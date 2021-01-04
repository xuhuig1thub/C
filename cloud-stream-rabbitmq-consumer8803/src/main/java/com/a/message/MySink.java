package com.a.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author XXX
 * @site www.muddywater.com
 * @company muddywater .corp
 * @create 2020-11-29 11:28
 */
public interface MySink {

    /**
     * Input channel name.
     */
    String INPUT = "input33";

    /**
     * @return input channel.
     */
    @Input(INPUT)
    SubscribableChannel input();

}
