package com.hcy.core.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.net.UnknownHostException;

/**
 * @ClassName: ReidsConfig
 * @Author: hcy
 * @Description:
 * @Date: 2019-06-02 09:46
 * @Version: 1.0
 **/
@Configuration
public class ReidsConfig {

    @Bean
    public RedisTemplate<String, Object> objectRedisTemplate1(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate();
        Jackson2JsonRedisSerializer<Object> jsonSerial = new Jackson2JsonRedisSerializer(Object.class);
        //修复反序列化bug
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jsonSerial.setObjectMapper(om);
        template.setDefaultSerializer(jsonSerial);
        template.setKeySerializer(RedisSerializer.string());
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }

    @Bean
    public RedisTemplate<String, Object> objectRedisTemplate2(RedisConnectionFactory redisConnectionFactory) throws UnknownHostException {
        RedisTemplate<String, Object> template = new RedisTemplate();
        Jackson2JsonRedisSerializer<Object> jsonSerial = new Jackson2JsonRedisSerializer(Object.class);
        template.setDefaultSerializer(jsonSerial);
        template.setKeySerializer(RedisSerializer.string());
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }
}
