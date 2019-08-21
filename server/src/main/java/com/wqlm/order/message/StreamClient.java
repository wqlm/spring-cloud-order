package com.wqlm.order.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author wqlm
 * @date 2019/8/14 16:25
 */
public interface StreamClient {

    String INPUT ="InMyMessage";
    String OUTPUT ="outMyMessage";

    String INPUT2 ="InMessage2";

    String OUTPUT2 ="outMyMessage2";

    @Input(StreamClient.INPUT)
    SubscribableChannel input();

    @Output(StreamClient.OUTPUT)
    MessageChannel output();

    @Input(StreamClient.INPUT2)
    SubscribableChannel input2();

    @Output(StreamClient.OUTPUT2)
    MessageChannel output2();
}
