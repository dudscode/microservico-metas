package com.fase2.meta.config;

import com.fase2.meta.model.CacheProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;

@Configuration
@RequiredArgsConstructor
public class RedisConfiguration {
    private final CacheProperties cacheProperties;
    @Bean
    public RedisStandaloneConfiguration redisStandaloneConfiguration() {
        var redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setPassword(cacheProperties.getPassword());
        return redisStandaloneConfiguration;
    }
}
