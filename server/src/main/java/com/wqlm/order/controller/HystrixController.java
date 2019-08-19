package com.wqlm.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import feign.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    // 配置超时时间
//    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" ,value = "4000")
//    })
    @HystrixCommand(commandProperties = {
            // 启用熔断器
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            // 请求数量阀值,只有在一个统计窗口内处理的请求数量达到这个阈值，才会进行熔断与否的判断
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),
            // 睡眠窗口毫秒值,睡眠窗口内的请求被降级处理，窗口期到后，再有请求过来会试探服务是否可用，如果可用关闭熔断器，否则 重新进入睡眠窗口
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            // 错误百分比阀值, 默认30,达到后启动熔断
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50")
    })
    @GetMapping("/getProductInfoList")
    public String getProductInfoList(@RequestParam("token") String token) {
        if("1".equals(token)){
            return "true";
        }
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
