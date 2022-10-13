package com.itheima.templete;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    
    @Test
    public void testRedisTemplate(){
        redisTemplate.opsForValue().set("name","¾íÐÄ²Ì");
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
}
