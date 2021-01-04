package com.a.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-29 11:33
 */
public interface MySource {

    /**
     * Name of the output channel.
     */
    String OUTPUT = "fdsoutput";

    /**
     * @return output channel
     */
    @Output(OUTPUT)
    MessageChannel output();

}

