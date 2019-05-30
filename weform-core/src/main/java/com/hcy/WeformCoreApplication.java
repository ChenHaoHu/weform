package com.hcy;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(scanBasePackages="com.hcy")
@EnableDubboConfiguration
@EnableTransactionManagement
@EnableScheduling
@EnableAsync
public class WeformCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeformCoreApplication.class, args);
    }

}
