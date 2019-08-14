package com.wqlm.order.message;

import com.wqlm.order.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

/**
 * Stream 消息的接收端
 *
 * @author wqlm
 * @date 2019/8/14 16:29
 */
@Component
@EnableBinding(StreamClient.class)
@Slf4j
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void process(Object message) {
//        log.info("message:${}", message);
//    }


    @StreamListener(StreamClient.INPUT)
    // @SendTo 将 process 方法的返回值 发送给 INPUT2
    @SendTo(StreamClient.INPUT2)
    public String process(OrderDTO message) {
        log.info("message:${}", message);
        return "消息收到，处理成功";
    }

    /**
     * 接收 INPUT2 的消息
     * @param message
     * @return
     */
    @StreamListener(StreamClient.INPUT2)
    public void process2(String message) {
        log.info("message:${}", message);
    }

}
