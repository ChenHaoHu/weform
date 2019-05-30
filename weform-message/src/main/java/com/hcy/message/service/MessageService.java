package com.hcy.message.service;


import org.springframework.stereotype.Service;
/**
 * 短信提醒回复
 */
@Service
public interface MessageService {

    boolean sendVerificationCode(String phoneNumber, String code);
    boolean sendActvityBeginTempMessage(String phoneNumber, String str1, String str2, String str3);
    boolean sendActvitySignEndTempMessage(String phoneNumber, String str1, String str2);
}
