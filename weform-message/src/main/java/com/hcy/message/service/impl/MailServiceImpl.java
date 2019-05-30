package com.hcy.message.service.impl;

import com.hcy.message.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @ClassName: MailServiceImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-23 16:06
 * @Version: 1.0
 **/

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String MAIL_USERNAME;

    @Override
    public boolean sendSimpleMail(String sendMail, String topic, String content) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(MAIL_USERNAME);
            message.setTo(sendMail);
            message.setSubject(topic);
            message.setText(content);
            javaMailSender.send(message);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
