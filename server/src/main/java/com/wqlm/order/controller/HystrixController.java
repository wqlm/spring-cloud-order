package com.wqlm.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.persistence.NamedStoredProcedureQuery;
import java.util.Arrays;

/**
 * @author wqlm
 * @date 2019/8/18 15:57
 */
@RestController
// 指定默认降级方法为 defaultFallback ，如果 @HystrixCommand 没有指定 fallbackMethod，则默认降级到该方法
@DefaultProperties(defaultFallback = "defaultFallback")
public class HystrixController {

//    @HystrixCommand(fallbackMethod = "fallback")
    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "4000")
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForObject("http://localhost:8051/product/listForOrder",
                Arrays.asList("157875196366160022"), String.class);
//                throw new RuntimeException("出现异常");
    }


    /**
     * 降级处理代码
     * @return
     */
    public String fallback(){
        // getProductInfoList方法抛出异常、目标服务无法访问，需要降级处理的代码
        return "请稍后再试";
    }

    /**
     * 默认降级处理代码
     * @return
     */
    public String defaultFallback(){
        // 如果 @HystrixCommand 没有指定 fallbackMethod，则默认降级到该方法
        return "默认降级处理代码";
    }

}
