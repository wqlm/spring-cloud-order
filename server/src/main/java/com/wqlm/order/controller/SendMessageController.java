package com.wqlm.order.controller;

import com.wqlm.order.dto.OrderDTO;
import com.wqlm.order.message.StreamClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息发送端
 *
 * @author wqlm
 * @date 2019/8/14 16:39
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMessage")
//    public void send() {
//        streamClient.output().send(MessageBuilder.withPayload("消息内容").build());
//    }

    /**
     * 发送 OrderDTO 对象
     */
    @GetMapping("/sendMessage")
    public void send() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName("苹果");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
