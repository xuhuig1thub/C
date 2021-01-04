package com.a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-18 12:08
 */
@Configuration
public class FeignConfig {
    @Bean
    Logger.Level fdsfsd(){
        return Logger.Level.FULL;
    }
}
