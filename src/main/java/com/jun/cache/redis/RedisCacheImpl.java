package com.jun.cache.redis;

import com.jun.cache.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
@Component
public class RedisCacheImpl implements CacheService {

    /**
     * 默认过期时间 600s
     */
    private static final Long DEFAULT_EXPIRE = 600L;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public <T> void set(String key, T obj) {
        set(key,obj,DEFAULT_EXPIRE);
    }

    @Override
    public <T> void set(String key, T obj, Long expireTime) {

        if(StringUtils.isEmpty(key) || obj == null){
            return;
        }
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        ops.set(key,obj);
        redisTemplate.expire(key,expireTime,TimeUnit.SECONDS);

    }

    @Override
    public <T> T get(String key) {
        return get(key,null,null,DEFAULT_EXPIRE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function) {
        return get(key,function,null,DEFAULT_EXPIRE);
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm) {
        return get(key,function,funcParm,DEFAULT_EXPIRE);
    }

    @Override
    public <T> T get(String key, Function<String, T> function, Long expireTime) {
        return get(key,function,null,DEFAULT_EXPIRE);
    }

    @Override
    public <T, M> T get(String key, Function<M, T> function, M funcParm, Long expireTime) {

        T obj = null;
        if(StringUtils.isEmpty(key)){
            return null;
        }
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        obj = (T)ops.get(key);

        if(obj==null && function != null){
            obj = function.apply(funcParm);
            set(key,obj,expireTime);
        }

        return obj;
    }

    @Override
    public void remove(String key) {
        if(StringUtils.isEmpty(key)){
            return;
        }
        redisTemplate.delete(key);
    }

    @Override
    public Boolean containsKey(String key) {
        if(StringUtils.isEmpty(key)){
            return false;
        }else{
            return redisTemplate.hasKey(key);
        }
    }
}
