package com.wqlm.order.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ClientController {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        // 方式1，使用 RestTemplate 通过指定地址访问其他服务
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject("http://localhost:8081/msg",String.class);

        // 第二种方式，使用 RestTemplate 通过 LoadBalancerClient.choose("服务名称") 获取一个服务地址进行访问
//        ServiceInstance serviceInstance = loadBalancerClient.choose("product");
//        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/msg";
//        RestTemplate restTemplate = new RestTemplate();
//        String response = restTemplate.getForObject(url,String.class);

        // 第三种方式， 添加 RestTemplateConfig 配置类，在 RestTemplate 上使用 @LoadBalanced 注解，然后通过 http://服务名/接口来访问
        String response = restTemplate.getForObject("http://product/msg", String.class);
        return response;
    }

}
