package com.wqlm.order.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接受 mq 消息
 *
 * @author wqlm
 * @date 2019/8/14 10:54
 */
@Slf4j
@Component
public class MqReceiver {

    //1.监听 myQueue 这个消息队列,前提是 myQueue 这个队列已存在
    //@RabbitListener(queues = "myQueue")

    //2.监听 myQueue 这个消息队列，没有则创建
    //@RabbitListener(queuesToDeclare = @Queue("myQueue"))

    //3.监听 myQueue 这个消息队列，没有则创建 Exchanges 和 Queue ,并将他们绑定
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("myQueue"),
            exchange = @Exchange("myExchange")
    ))
    public void process(String message) {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 查询订单 队列 queryOrder 绑定 myExchange,设置路由 queryOrder
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            key = "queryOrder",
            value = @Queue("queryOrder")
    ))
    public void queryOrder(String message) {
        log.info("MqReceiver:{}", message);
    }

    /**
     * 下单 队列 buy 绑定 myExchange,设置路由 buy
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            exchange = @Exchange("myExchange"),
            key = "buy",
            value = @Queue("buy")
    ))
    public void buy(String message) {
        log.info("MqReceiver:{}", message);
    }
}
