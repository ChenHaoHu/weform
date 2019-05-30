package com.hcy.message.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;



@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceImplTest {

    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail() {

        mailService.sendSimpleMail("775656764@qq.com","topic","内容");


    }
}