package com.a.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-16 23:24
 */
@RestController
@Slf4j
public class OrderZKController {
    public static final String INVOKE_URL="http://cloud-provider-payment";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping(value="/consumer/payment/zk")
    public String paymentInfo(){
        log.info("paymentInfo()");
        String result=restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class   );
        return result;

    }
}
