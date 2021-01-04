package com.a.service.impl;

import com.a.messaging.MySource;
import com.a.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * @ClassName: MessageProviderImpl
 * @description:
 * @author: XZQ
 * @create: 2020/3/10 15:06
 **/
@EnableBinding(MySource.class)//定义消息的推送管道
public class MessageProviderImpl implements IMessageProvider {

    @Autowired
    private MessageChannel fdsoutput;//消息发送通道

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        fdsoutput.send(MessageBuilder.withPayload(serial).build());
        System.out.println("*****serial***" + serial);
        return serial;
    }
}
