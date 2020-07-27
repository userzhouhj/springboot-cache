package com.jun.cache.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@ConfigurationProperties(prefix = "cache.local.guava")
public class LocalCacheProperties {

    /**
     * guava本地缓存的最大容量
     */
    private Long maxSize;
    /**
     * guava本地缓存 当最后一次写入后的移除时间
     */
    private Long afterWriteExpire;
    /**
     * guava本地缓存 当最后一次访问后的移除时间
     */
    private Long afterAccessExpire;

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public Long getAfterWriteExpire() {
        return afterWriteExpire;
    }

    public void setAfterWriteExpire(Long afterWriteExpire) {
        this.afterWriteExpire = afterWriteExpire;
    }

    public Long getAfterAccessExpire() {
        return afterAccessExpire;
    }

    public void setAfterAccessExpire(Long afterAccessExpire) {
        this.afterAccessExpire = afterAccessExpire;
    }
}
