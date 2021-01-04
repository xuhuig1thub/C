package com.a.controller;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-13 9:25
 */
@RestController
@Slf4j
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value="/payment/zk")
    public String paymentzk(){
        String uuid=UUID.randomUUID().toString();
        System.out.println("springcloud with zookeeper: "+serverPort+"\t"+uuid );
        return "springcloud with zookeeper: "+uuid;
    }

}
