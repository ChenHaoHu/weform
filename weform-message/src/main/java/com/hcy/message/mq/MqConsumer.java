package com.hcy.message.mq;

import com.hcy.message.config.RabbitMqConfig;
import com.hcy.message.mapper.FormMapper;
import com.hcy.message.mapper.TimeTaskMapper;
import com.hcy.message.mapper.UserMapper;
import com.hcy.message.model.SimpleMessgaeTempl;
import com.hcy.message.model.TimeTask;
import com.hcy.message.model.UserDO;
import com.hcy.message.service.MailService;
import com.hcy.message.service.MessageService;
import com.rabbitmq.client.Channel;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: MqConsumer
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-25 18:11
 * @Version: 1.0
 **/

@Service
public class MqConsumer {

    private Logger log = LoggerFactory.getLogger(MqConsumer.class);

    @Autowired
    TimeTaskMapper timeTaskMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FormMapper formMapper;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    MailService mailService;

    @Autowired
    MessageService messageService;

    @RabbitListener(queues = RabbitMqConfig.TASK_HANDLE_QUEUE)
    public void process_TASK_HANDLE_QUEUE(Message message, String taskKey, Channel channel) throws IOException {
        log.info("TASK_HANDLE_QUEUE receive: " + new String(message.getBody()));
        TimeTask taskByTaskKey = timeTaskMapper.getTaskByTaskKey(taskKey);
        if (taskByTaskKey == null){
            log.info("TASK_HANDLE_QUEUE " + taskKey + "没有具体信息，故抛弃");
        }else{
            Integer sendType = taskByTaskKey.getSendType(); //发送类型
            Integer taskType = taskByTaskKey.getTaskType(); //任务类型
            Integer formId = taskByTaskKey.getFormId();
            Integer userId = taskByTaskKey.getUserId();
            Map<String, String> user = userMapper.findUserNameAndEmailAndPhoneByUserId(userId);
            Map<String, String> form = formMapper.getFormTitleAndStartTime(formId);
            SimpleMessgaeTempl data = new SimpleMessgaeTempl();


            if (sendType == 0){
                String con = "";

                //0---- 项目参加提醒 1----项目结束提醒
                if (taskType == 0){
                    con = "尊敬的汇表当用户,"+user.get("name")+"先生/女士,您好！！！，汇表单小助手提醒您，您报名的项目"+form.get("title")+"将于" +form.get("start")+ "开始。";

                }else if(taskType == 1){
                    con = "尊敬的汇表当用户,"+user.get("name")+"先生/女士,您好！！！，汇表单小助手提醒您，您创建的项目"+form.get("title")+"报名即将结束，请进入小程序查看报名结果";
                }
                //邮件
                String email = user.get("email");
                if (email !=null){
                    data.setSend(email);
                    HashMap<String,String> map = new HashMap<>();
                    map.put("con",con);
                    data.setContent(map);
                    data.setTopic("汇表单提醒");
                    rabbitTemplate.convertAndSend(RabbitMqConfig.MESSGAE_EXCHANGE,RabbitMqConfig.MAIL_MESSAGE_KEY,data);
                }else{
                    log.info("用户获取email失败："+userId);
                }

            }else if(sendType == 1){
                //微信消息提醒

            }else if(sendType == 2){
                HashMap<String,String> map = new HashMap<>();
                //0---- 项目参加提醒 1----项目结束提醒
                if (taskType == 0){
                    map.put("type","0");
                    map.put("key1",user.get("name"));
                    map.put("key2",form.get("title"));
                    map.put("key3",form.get("start"));
                }else if(taskType == 1){
                    map.put("type","1");
                    map.put("key1",user.get("name"));
                    map.put("key2",form.get("title"));
                }

                //短信提醒
                String phone = user.get("phone");
                if (phone !=null){
                    data.setSend(phone);
                    data.setContent(map);
                    data.setTopic("汇表单提醒");
                    rabbitTemplate.convertAndSend(RabbitMqConfig.MESSGAE_EXCHANGE,RabbitMqConfig.PHONE_MESSAGE_KEY,data);
                }else{
                    log.info("用户获取phone失败："+userId);
                }

            }
        }
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = RabbitMqConfig.PHONE_MESSAGE_QUEUE)
    public void process_PHONE_MESSAGE_QUEUE(Message message,SimpleMessgaeTempl templ, Channel channel) throws IOException {

        Map<String, String> content = templ.getContent();
        String type  = content.get("type");
        if ("0".equals(type)){
            messageService.sendActvityBeginTempMessage(templ.getSend(),content.get("key1"),content.get("key2"),content.get("key3").substring(0,10));
        }
        if ("1".equals(type)){
            messageService.sendActvitySignEndTempMessage(templ.getSend(),content.get("key1"),content.get("key2"));

        }

        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = RabbitMqConfig.WECHAT_MESSAGE_QUEUE)
    public void process_WECHAT_MESSAGE_QUEUE(Message message ,SimpleMessgaeTempl templ,Channel channel) throws IOException {
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    @RabbitListener(queues = RabbitMqConfig.MAIL_MESSAGE_QUEUE)
    public void process_MAIL_MESSAGE_QUEUE(Message message,SimpleMessgaeTempl templ, Channel channel) throws IOException {
        mailService.sendSimpleMail(templ.getSend(),templ.getTopic(),templ.getContent().get("con"));
        // 采用手动应答模式, 手动确认应答更为安全稳定
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }
}
