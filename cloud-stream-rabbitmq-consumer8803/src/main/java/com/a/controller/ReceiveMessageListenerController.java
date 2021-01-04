package com.a.controller;

import com.a.message.MySink;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-27 12:54
 */
@Component
@EnableBinding(MySink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;
    @StreamListener(MySink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者8803，-------" + message.getPayload() + "\t port:" + serverPort);
    }

}