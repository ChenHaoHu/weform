package com.hcy.message.config;

import com.github.qcloudsms.SmsSingleSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: MessageConfig
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-23 17:46
 * @Version: 1.0
 **/

@Configuration
public class MessageConfig {
    @Value("${spring.message.appid}")
    private int appid;
    @Value("${spring.message.appkey}")
    private String appkey;

    @Bean
    public SmsSingleSender getSmsSingleSender(){
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        return ssender;
    }
}
