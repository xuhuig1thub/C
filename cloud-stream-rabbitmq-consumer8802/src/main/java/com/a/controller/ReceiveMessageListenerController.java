package com.a.controller;

import com.a.message.MySink;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(MySink.class)
public class ReceiveMessageListenerController {

    @Value("${server.port}")
    private String serverPort;

    @StreamListener(MySink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者8802，-------" + message.getPayload() + "\t port:" + serverPort);
    }

}