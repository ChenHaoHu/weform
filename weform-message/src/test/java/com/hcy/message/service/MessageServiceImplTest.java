package com.hcy.message.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @Test
    public void sendVerificationCode() {
        boolean b = messageService.sendVerificationCode("18642316507", "9527");
        System.out.println(b );
    }
}