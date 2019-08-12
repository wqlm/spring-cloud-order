package com.wqlm.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @FeignClient 声明 ProductClient 是一个 Feign 客户端
 * product 是要调用的服务
 */
@FeignClient("product")
public interface ProductClient {

    /**
     * /msg 是要调用的 product 服务中的接口
     * Feign 将远程接口的调用绑定到本地方法上
     * @return
     */
    @GetMapping("/msg")
    String productMsg();
}
