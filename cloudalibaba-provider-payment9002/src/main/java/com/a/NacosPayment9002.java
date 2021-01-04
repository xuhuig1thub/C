package com.a;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName: Payment9001
 * @description:
 * @author: XZQ
 * @create: 2020/3/11 11:37
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosPayment9002 {
    public static void main(String[] args) {
        SpringApplication.run(NacosPayment9002.class, args);
    }
}
