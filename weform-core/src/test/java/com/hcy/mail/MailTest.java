package com.hcy.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: MailTest
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-19 22:53
 * @Version: 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTest {

    @Autowired
    JavaMailSender javaMailSender;



    @Test
    public void sendSimpleMail() throws Exception {

    }
}
