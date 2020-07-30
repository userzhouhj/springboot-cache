package com.jun.cache.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */

@Configuration
public class RedisCacheConfiguration {

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();


        template.setKeySerializer(new StringRedisSerializer());

        ////设置value的序列化器  默认值是JdkSerializationRedisSerializer
        //使用Jackson序列化器的问题是，复杂对象可能序列化失败，比如JodaTime的DateTime类型

        //使用Jackson2，将对象序列化为JSON

        template.setValueSerializer(new JdkSerializationRedisSerializer());

        //将redis连接工厂设置到模板类中
        template.setConnectionFactory(redisConnectionFactory);

        return template;
    }
}
