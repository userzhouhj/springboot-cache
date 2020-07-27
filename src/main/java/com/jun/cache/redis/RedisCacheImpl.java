package com.jun.cache.redis;

import com.jun.cache.CacheService;

import java.util.function.Function;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class RedisCacheImpl implements CacheService {

    @Override
    public <T> void set(String key, Object T) {

    }

    @Override
    public <T> void set(String key, Object T, Long expireTime) {

    }

    @Override
    public <T> T get(String key) {
        return null;
    }

    @Override
    public <T> T get(String key, Function<String, T> function) {
        return null;
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm) {
        return null;
    }

    @Override
    public <T> T get(String key, Function<String, T> function, Long expireTime) {
        return null;
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm, Long expireTime) {
        return null;
    }

    @Override
    public void remove(String key) {

    }

    @Override
    public Boolean containsKey(String key) {
        return null;
    }
}
