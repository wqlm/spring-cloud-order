package com.wqlm.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;


/**
 * 云端配置
 * girl:
 *   name: lm
 *   age: 17
 * @ConfigurationProperties("girl") 匹配 girl 开头的配置
 * @RefreshScope 刷新配置
 */
@Data
@Component
@ConfigurationProperties("girl")
@RefreshScope
public class GirlConfig {

    private String name;

    private String age;
}
