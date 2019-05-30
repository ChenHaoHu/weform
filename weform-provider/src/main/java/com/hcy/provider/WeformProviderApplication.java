package com.hcy.provider;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class WeformProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeformProviderApplication.class, args);
    }

}
