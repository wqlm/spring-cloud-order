package com.wqlm.order.client;

import com.wqlm.order.dataobject.ProductInfo;
import com.wqlm.order.dto.CartDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

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


    @PostMapping("/product/listForOrder")
    List<ProductInfo> listForOrder(@RequestBody List<String> productIdList);

    @PostMapping("/product/decreaseStock")
    void decreaseStock(@RequestBody List<CartDTO> cartDTOList);


}
