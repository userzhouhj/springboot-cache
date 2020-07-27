package com.jun.cache.local;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Maps;
import com.jun.cache.CacheService;
import com.jun.cache.config.LocalCacheProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class LocalGuavaCacheImpl implements CacheService {

    @Autowired
    private LocalCacheProperties localCacheProperties;

    private static Map<String, Cache<String,Object>> cacheMap = Maps.newConcurrentMap();

    public LocalGuavaCacheImpl(){
        Cache<String, Object> cache = CacheBuilder.newBuilder()
                .maximumSize(localCacheProperties.getMaxSize())
                .expireAfterWrite(localCacheProperties.getAfterWriteExpire(), TimeUnit.SECONDS)
                .expireAfterAccess(localCacheProperties.getAfterAccessExpire(), TimeUnit.SECONDS)
                .recordStats()
                .build();
        cacheMap.put(String.valueOf(localCacheProperties.getAfterWriteExpire()),cache);

    }

    @Override
    public <T> void set(String key, Object T) {
        set(key,T,localCacheProperties.getAfterWriteExpire());
    }

    @Override
    public <T> void set(String key, Object T, Long expireTime) {

        if(StringUtils.isEmpty(key)){
            return;
        }
        expireTime= getExpire(expireTime);

        Cache<String, Object> cache = getCache(expireTime);

        cache.put(key,T);
    }

    @Override
    public <T> T get(String key) {
        return this.get(key,null,null,localCacheProperties.getAfterWriteExpire());
    }

    @Override
    public <T> T get(String key, Function<String, T> function) {
        return this.get(key,function,null,localCacheProperties.getAfterWriteExpire());
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm) {
        return this.get(key,function,funcParm,localCacheProperties.getAfterWriteExpire());
    }

    @Override
    public <T> T get(String key, Function<String, T> function, Long expireTime) {
        return this.get(key,function,null,expireTime);
    }

    @Override
    public <T extends Object, M extends Object> T get(String key, Function<M, T> function, M funcParm, Long expireTime) {

        T obj = null;
        //首先判断键是否为空
        if(StringUtils.isEmpty(key)){
            return null;
        }
        expireTime = getExpire(expireTime);
        Cache<String, Object> cache = getCache(expireTime);

        if(function == null){
            obj = (T)cache.getIfPresent(key);
        }else{
            try {
                obj = (T)cache.get(key,() -> {
                    T apply = function.apply(funcParm);
                    return apply;
                });
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    @Override
    public void remove(String key) {

        if(StringUtils.isEmpty(key)){
            return;
        }
        Long expireTime= getExpire(localCacheProperties.getAfterWriteExpire());

        Cache<String, Object> cache = getCache(expireTime);
        cache.invalidate(key);

    }

    @Override
    public Boolean containsKey(String key) {

        if(StringUtils.isEmpty(key)){
            return false;
        }
        Object o = get(key);
        if(o == null){
            return false;
        }
        return true;
    }

    private Long getExpire(Long expireTime){
        return (expireTime==null || expireTime < localCacheProperties.getAfterWriteExpire()) ? localCacheProperties.getAfterWriteExpire() : expireTime;
    }

    private Cache<String,Object> getCache(Long expireTime){

        if(expireTime == null){
            return null;
        }
        Cache<String,Object> cache = null;
        String cacheKey = String.valueOf(expireTime);
        if(cacheMap.containsKey(cacheKey)){
            cache = cacheMap.get(cacheKey);
        }else{
            cache = CacheBuilder.newBuilder()
                    .maximumSize(localCacheProperties.getMaxSize())
                    .expireAfterWrite(expireTime, TimeUnit.SECONDS)
                    .expireAfterAccess(localCacheProperties.getAfterAccessExpire(), TimeUnit.SECONDS)
                    .recordStats()
                    .build();
            cacheMap.put(String.valueOf(expireTime),cache);
        }

        return cache;
    }
}
