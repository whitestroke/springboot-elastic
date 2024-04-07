package com.lct.springbootjar.springbootes.config.elastic;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author :lct
 * @date : 2024/4/7
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "es")
public class ElasticConfig {

    private String ip;
    private Integer port;
    private String username;
    private String password;
}
