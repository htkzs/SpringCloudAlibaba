package com.itheima.provider.template;

import com.itheima.provider.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedisTemplate(){
        redisTemplate.opsForValue().set("name","Alibaba");
        String name = (String)redisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    // String ����
    @Test
    public void testString () {
        redisTemplate.opsForValue().set("user", "xiaobai");
        Object name = redisTemplate.opsForValue().get("user");
        System.out.println(name);
    }

    // Hash ���� �洢һ������
    @Test
    public void testHash () {
        redisTemplate.opsForHash().put("user1", "name", "clarence");
        redisTemplate.opsForHash().put("user1", "age", "25");
        Map map = redisTemplate.opsForHash().entries("user1");
        System.out.println(map);
    }

    // List ����
    @Test
    public void testList () {
        redisTemplate.opsForList().leftPushAll("names", "xiaobai", "xiaohei", "xiaolan");
        List<Object> names = redisTemplate.opsForList().range("names", 0, 3);
        System.out.println(names);
    }

    // Set ����
    @Test
    public void testSet () {
        redisTemplate.opsForSet().add("set", "a", "b", "c");
        Set<Object> set = redisTemplate.opsForSet().members("set");
        System.out.println(set);
    }

    // SortedSet ����
    @Test
    public void testSortedSet () {
        redisTemplate.opsForZSet().add("class", "xiaobai", 90);
        //֧��һ����Χ����
        Set aClass = redisTemplate.opsForZSet().rangeByScore("class", 90, 100);
        System.out.println(aClass);
        Set<ZSetOperations.TypedTuple<Object>> set = new HashSet<>();
        set.add(new DefaultTypedTuple<>("xiaohei", 88.0));
        set.add(new DefaultTypedTuple<>("xiaohui", 94.0));
        set.add(new DefaultTypedTuple<>("xiaolan", 84.0));
        set.add(new DefaultTypedTuple<>("xiaolv", 82.0));
        set.add(new DefaultTypedTuple<>("xiaohong", 99.0));
        redisTemplate.opsForZSet().add("class", set);
        //��������
        Set aClass1 = redisTemplate.opsForZSet().range("class", 0, 6);
        System.out.println(aClass1);
    }
    //���Դ���һ����������
    @Test
    public void testSaveUser() {
        redisTemplate.opsForValue().set("user", new User("С��", 23));
        User user = (User) redisTemplate.opsForValue().get("user");
        System.out.println(user);
    }
    //����һ��������ʱ���key ����10s����
    @Test
    public void setExpire(){
        redisTemplate.opsForValue().set("num","123",10, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("num"));

    }
    /*
    getAndSet V getAndSet(K key, V value);
    ���ü����ַ���ֵ���������ֵ
     */
    @Test
    public void testGetAndSet(){
        redisTemplate.opsForValue().set("getSetTest","test");
        System.out.println(redisTemplate.opsForValue().getAndSet("getSetTest","test2"));

    }
    /*
    size Long size(K key);
    ����key����Ӧ��valueֵ�ó���
     */
    @Test
    public void testSize(){
        redisTemplate.opsForValue().set("key","hello world");
        System.out.println("***************"+redisTemplate.opsForValue().size("key"));
    }




}
