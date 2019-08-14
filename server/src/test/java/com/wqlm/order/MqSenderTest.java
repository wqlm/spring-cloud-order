package com.wqlm.order;

import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *  发送 mq 消息
 * @author wqlm
 * @date 2019/8/14 11:19
 */
@Component
public class MqSenderTest extends OrderApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void send(){
        // 第一个参数是要发送的队列的名字，第二个参数是消息内容
        amqpTemplate.convertAndSend("myQueue", "content");
    }

    @Test
    public void buy(){
        // 第一个参数是路由器的名字，第二个参数是路由规则，第三个参数是消息内容
        amqpTemplate.convertAndSend("myExchange","buy",
                "向 myExchange 交换机发送购买商品消息，指定路由规则为buy");
    }
}
