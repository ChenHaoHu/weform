package com.hcy.message.service;


import org.springframework.stereotype.Service;

/**
 * 邮件提醒回复
 */
@Service
public interface MailService {
   boolean sendSimpleMail(String sendMail, String topic, String content);
}
