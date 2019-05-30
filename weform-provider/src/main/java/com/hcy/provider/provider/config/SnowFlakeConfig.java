package com.hcy.provider.provider.config;

import com.hcy.provider.provider.service.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: SnowFlakeConfig
 * @Author: hcy
 * @Description:
 * @Date: 2019-05-17 20:21
 * @Version: 1.0
 **/
@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowFlake getSnowFlake(){
        SnowFlake snowFlake = new SnowFlake();
        return snowFlake;
    }
}
