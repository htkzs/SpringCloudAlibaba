package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@SpringBootApplication
@EnableRedisRepositories
public class SpringCloudAlibabaDemoApplication {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 配置redisTemplate
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer); // key序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); // value序列化
        redisTemplate.setHashKeySerializer(stringSerializer); // Hash key序列化
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer()); // Hash value序列化
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaDemoApplication.class, args);
    }
}
