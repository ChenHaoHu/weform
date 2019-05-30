package com.hcy.message.service.impl;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.hcy.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.json.JSONException;

import java.io.IOException;

/**
 * @ClassName: MessageServiceImpl
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-23 17:45
 * @Version: 1.0
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    SmsSingleSender ssender;

    @Value("${spring.message.smsSign}")
    private String smsSign;
    @Value("${spring.message.verTemp}")
    private int verTemp;
    @Value("${spring.message.ActvityBeginTemp}")
    private int ActvityBeginTemp;
    @Value("${spring.message.ActvitySignEndTemp}")
    private int ActvitySignEndTemp;

    @Override
    public boolean sendVerificationCode(String phoneNumber, String code) {
        String[] params = {code,"2"};
        try{
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, verTemp, params,smsSign, "", "");
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean sendActvityBeginTempMessage(String phoneNumber,String str1, String str2, String str3) {
        String[] params = {str1,str2,str3};
        try{
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, ActvityBeginTemp, params,smsSign, "", "");
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean sendActvitySignEndTempMessage(String phoneNumber,String str1, String str2) {
        String[] params = {str1,str2};
        try{
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, ActvitySignEndTemp, params,smsSign, "", "");
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return true;
    }
}
