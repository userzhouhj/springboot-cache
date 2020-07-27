package com.jun.cache.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@EnableConfigurationProperties(LocalCacheProperties.class)
@Configuration
public class LocalCacheGuavaConfiguration {
}
