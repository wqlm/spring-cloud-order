package com.wqlm.order.message;

import com.fasterxml.jackson.core.type.TypeReference;
import com.sun.javafx.binding.StringFormatter;
import com.wqlm.order.utils.JsonUtil;
import com.wqlm.product.common.ProductInfoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wqlm
 * @date 2019/8/15 16:53
 */
@Component
@Slf4j
public class productInfoReceiver {

    private static final String PRODUCT_STOCK_TEMPLATE = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        // 将接受到的 json 字符串转成 ProductInfoOutput
        List<ProductInfoOutput> productInfoOutputList = (List<ProductInfoOutput>) JsonUtil.fromJson(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        log.info("从队列【{}】接收到消息{}", "productInfo", productInfoOutputList);
        if(productInfoOutputList == null){
            return;
        }

        for (ProductInfoOutput productInfoOutput : productInfoOutputList) {
            stringRedisTemplate.opsForValue().set(StringFormatter.format(PRODUCT_STOCK_TEMPLATE, productInfoOutput.getProductId()).toString(),
                    productInfoOutput.getProductStock().toString());
        }

    }
}
