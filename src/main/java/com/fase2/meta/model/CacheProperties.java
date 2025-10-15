package com.fase2.meta.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(value = "spring.data.redis")
public class CacheProperties {
    private String host;
    private Integer port;
    private String password;
}
