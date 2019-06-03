package com.hcy.core.timer;

import com.hcy.core.config.RabbitMqConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

/**
 * @ClassName: timer
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-19 00:37
 * @Version: 1.0
 **/

//@Configuration//配置类
//@EnableScheduling//支持定时任务类
public class Timer {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    //todo: 有问题
    private String TIME_TASK_REDIS_ZSET = "TimeTask";

    private Object obj = new Object();


    //log
    Logger log = LoggerFactory.getLogger(Timer.class);

    @Scheduled(fixedRate = 3000)
    @Async
    public void handleTimeTask() {
        Set<String> set;

        //有抢占数据，所以同步
        synchronized (obj){
            long l = System.currentTimeMillis() + 5 * 1000;
            //获取近五秒内的数据
            set = redisTemplate.opsForZSet().rangeByScore(TIME_TASK_REDIS_ZSET, 0,l);//zset
            if (set.isEmpty()){
                return;
            }else{
                //获取到了就给删除了，防止被多个线程多次处理
                 redisTemplate.opsForZSet().removeRangeByScore(TIME_TASK_REDIS_ZSET, 0, l);
            }
        }

        //处理数据的task到消息队列 慢慢执行 此处应该异步执行
        set.stream().forEach(s -> {
            log.info("send taskkey to mq: "+s );
            rabbitTemplate.convertAndSend(RabbitMqConfig.TASK_HANDLE_EXCHANGE,RabbitMqConfig.TASK_HANDLE_KEY,s);
        });


    }
}
