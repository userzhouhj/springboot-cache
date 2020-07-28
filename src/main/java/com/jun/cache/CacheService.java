package com.jun.cache;

import java.util.function.Function;

/**
 * 提供缓存基本接口
 */
public interface CacheService {

    /**
     * 设置缓存
     * @param key 缓存键
     * @param obj 缓存实体
     */
    <T extends Object> void set(String key,T obj);

    /**
     * 设置缓存添加过期时间
     * @param key 缓存键
     * @param obj 缓存实体
     * @param expireTime 固体时间 毫秒
     *
     */
    <T extends Object> void set(String key,T obj,Long expireTime);

    /**
     * 获取缓存
     * @param key 缓存键
     * @return 查询的缓存实体数据
     */
    <T extends Object> T get(String key);

    /**
     * 查询缓存
     * @param key 缓存键
     * @param function 缓存为空，callable返回对象可空
     * @return 缓存数据
     */
    <T extends Object> T get(String key, Function<String,T> function);

    /**
     *查缓存
     * @param key 缓存键
     * @param function 缓存为空，callable返回对象可空
     * @param funcParm 参数
     * @return 缓存对象
     */
    <T extends Object, M extends Object> T get(String key, Function<M, T> function, M funcParm);


    /**
     * 查询缓存
     *
     * @param key        缓存键 不可为空
     * @param function   如没有缓存，调用该callable函数返回对象 可为空
     * @param expireTime 过期时间（单位：毫秒） 可为空
     **/
    <T extends Object> T get(String key, Function<String, T> function, Long expireTime);

    /**
     * 查询缓存
     *
     * @param key        缓存键 不可为空
     * @param function   如没有缓存，调用该callable函数返回对象 可为空
     * @param funcParm   function函数的调用参数
     * @param expireTime 过期时间（单位：毫秒） 可为空
     **/
    <T extends Object, M extends Object> T get(String key, Function<M, T> function, M funcParm, Long expireTime);

    /**
     * 删除缓存
     * @param key 缓存键
     */
    void remove(String key);

    /**
     * 是否包含缓存
     * @param key 缓存键
     * @return 布尔值
     */
    Boolean containsKey(String key);
}
