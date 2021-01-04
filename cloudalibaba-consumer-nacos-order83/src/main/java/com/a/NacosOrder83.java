package com.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @ClassName: OrderNacosMain83
 * @description:
 * @author: XZQ
 * @create: 2020/3/11 15:35
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class NacosOrder83 {
    public static void main(String[] args) {
        SpringApplication.run(NacosOrder83.class, args);
    }
}
