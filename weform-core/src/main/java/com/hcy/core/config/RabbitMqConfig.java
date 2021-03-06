package com.hcy.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: 简单DI年华
 * @Date: 19-2-26 11:07
 * @Description:
 */

@Configuration
public class RabbitMqConfig {

    public final static  String TASK_HANDLE_QUEUE = "TASK_HANDLE_QUEUE";
    public final static  String TASK_HANDLE_EXCHANGE = "TASK_HANDLE_EXCHANGE";
    public final static  String TASK_HANDLE_KEY = "TASK_HANDLE_KEY";

    Logger log = LoggerFactory.getLogger(RabbitMqConfig.class);

    @Bean
    public Queue TASK_HANDLE_QUEUE(){
        return new Queue(TASK_HANDLE_QUEUE,true,false,false);
    }

    @Bean
    public DirectExchange TASK_HANDLE_EXCHANGE(){
        return new DirectExchange(TASK_HANDLE_EXCHANGE,true, false);
    }

    @Bean
    Binding bindingExchangeMessage(Queue TASK_HANDLE_QUEUE, DirectExchange TASK_HANDLE_EXCHANGE) {
        return BindingBuilder.bind(TASK_HANDLE_QUEUE).to(TASK_HANDLE_EXCHANGE).with(TASK_HANDLE_KEY);
    }

    /**
     * 定制化amqp模版
     *
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     */
    @Bean
    @ConditionalOnSingleCandidate(ConnectionFactory.class)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

       RabbitTemplate  rabbitTemplate = new RabbitTemplate( connectionFactory);
        // 消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);

        // 消息返回, yml需要配置 publisher-returns: true
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            String correlationId = message.getMessageProperties().getCorrelationId();
            log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });

        // 消息确认, yml需要配置 publisher-confirms: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                 log.debug("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.debug("消息发送到exchange失败,原因: {}", cause);
            }
        });

        return rabbitTemplate;
    }
}




