package com.hcy.core.config;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName: OSSConfig
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-16 23:34
 * @Version: 1.0
 **/
@Configuration
public class OSSConfig {

    @Value("${OSS.endpoint}")
    String endpoint;
    @Value("${OSS.accessKeyId}")
    String accessKeyId;
    @Value("${OSS.accessKeySecret}")
    String accessKeySecret;
    @Bean
    public OSSClient getOSSClient(){
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}
