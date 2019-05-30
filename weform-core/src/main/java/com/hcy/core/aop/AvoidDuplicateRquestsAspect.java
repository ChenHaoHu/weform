package com.hcy.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;  
import org.springframework.web.context.request.ServletRequestAttributes;  
  
import javax.servlet.http.HttpServletRequest;  
import java.util.Arrays;


/**
 * 防止重复请求
 */
@Aspect
@Component
public class AvoidDuplicateRquestsAspect {
    @Pointcut("execution(public * com.hcy.core.controller.*.*(..))")
    public void avoidDuplicateRequests(){}


    @Autowired
    RedisTemplate<String,String> redisTemplate;

    /**
     * 使用redis进行分布式锁，即当请求来到的时候根据请求的参数等生成一个key
     *
     * @param pjp
     * @return
     */
    @Around("avoidDuplicateRequests()")
    public Object arround(ProceedingJoinPoint pjp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String host = request.getRemoteHost();
        String url = request.getRequestURL().toString();
        String key = host+url;
        String s = redisTemplate.opsForValue().get(key);
        if ("true".equals(s)){
            return "重复请求";
        }else{
            redisTemplate.opsForValue().set(key,"true");
            try {
                Object o =  pjp.proceed();
                return o;
            } catch (Throwable e) {
                e.printStackTrace();
                return null;
            } finally {
                redisTemplate.delete(key);
            }
        }
    }

}