package com.wqlm.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableHystrix
// @SpringCloudApplication 包含上面三个注解
@SpringCloudApplication
@EnableFeignClients(basePackages = "com.wqlm.product.client")
@ComponentScan(basePackages = "com.wqlm")
@EnableHystrixDashboard
public class OrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
