package com.itheima.provider.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HashOperation {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /*
    Long delete(H key, Object... hashKeys);
    删除给定的哈希hashKeys
     */
    @Test
    public void testDelete(){
        redisTemplate.opsForHash().put("user1", "name", "clarence");
        redisTemplate.opsForHash().put("user1", "age", "25");
        Map<Object, Object> map = redisTemplate.opsForHash().entries("user1");
        System.out.println(map);
        redisTemplate.opsForHash().delete("user1","name");
        System.out.println(redisTemplate.opsForHash().entries("user1"));
    }
    /*
    Boolean hasKey(H key, Object hashKey);
    确定哈希hashKey是否存在
     */
    @Test
    public void testHashKey(){
        redisTemplate.opsForHash().put("user1", "name", "clarence");
        redisTemplate.opsForHash().put("user1", "age", "25");
        System.out.println(redisTemplate.opsForHash().hasKey("user1", "name"));
        System.out.println(redisTemplate.opsForHash().hasKey("user1", "age"));
    }
    /*
    HV get(H key, Object hashKey);
    从键中的哈希获取给定hashKey的值
     */
    @Test
    public void testGet(){
        redisTemplate.opsForHash().put("user1", "name", "clarence");
        redisTemplate.opsForHash().put("user1", "age", "25");
        System.out.println(redisTemplate.opsForHash().get("user1", "name"));
    }
    /*
     Set<HK> keys(H key);
     获取key所对应的散列表的key
     */
    @Test
    public void testKeys(){
        redisTemplate.opsForHash().put("user1", "name", "clarence");
        redisTemplate.opsForHash().put("user1", "age", "25");
        redisTemplate.opsForHash().put("user1", "sex", "man");
        Set<Object> keys = redisTemplate.opsForHash().keys("user1");
        System.out.println(keys);
        /*
        Long size(H key);
        获取key所对应的散列表的大小个数
         */
        System.out.println(redisTemplate.opsForHash().size("user1"));
        /*
        void putAll(H key, Map<? extends HK, ? extends HV> m);
        使用m中提供的多个散列字段设置到key对应的散列表中
         */
        Map<String,Object> map = new HashMap<>();
        map.put("name","xiao");
        map.put("age","24");
        map.put("sex","woman");
        redisTemplate.opsForHash().putAll("user1",map);
        System.out.println(redisTemplate.opsForHash().entries("user1"));

        /*
        void put(H key, HK hashKey, HV value);
        设置散列hashKey的值
         */
        /*
         List<HV> values(H key);
         获取整个哈希存储的值根据密钥
         */
        List<Object> user1 = redisTemplate.opsForHash().values("user1");
        System.out.println(user1);

        /*
        Cursor<Map.Entry<HK, HV>> scan(H key, ScanOptions options);
        使用Cursor在key的hash中迭代，相当于迭代器。
         */
        Cursor<Map.Entry<Object, Object>> cursor = redisTemplate.opsForHash().scan("user1", ScanOptions.NONE);
        while(cursor.hasNext()){
            Map.Entry<Object, Object> entry = cursor.next();
            System.out.println(entry.getKey()+":"+entry.getValue());
        }


    }
    /*
    testKeys() 运行结果为：
    [age, name, sex]
    3
    {age=24, name=xiao, sex=woman}
    [24, xiao, woman]
    age:24
    name:xiao
    sex:woman

     */


}
