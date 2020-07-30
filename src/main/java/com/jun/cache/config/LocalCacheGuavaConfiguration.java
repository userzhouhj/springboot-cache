package com.jun.cache.config;

import com.jun.cache.local.LocalGuavaCacheImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@EnableConfigurationProperties(LocalCacheProperties.class)
@Configuration
public class LocalCacheGuavaConfiguration {

    @Autowired
    private LocalCacheProperties localCacheProperties;

    @Bean
    public LocalGuavaCacheImpl localGuavaCacheImpl(){
        return new LocalGuavaCacheImpl(localCacheProperties);
    }
}
