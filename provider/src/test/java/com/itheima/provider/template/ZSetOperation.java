package com.itheima.provider.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ZSetOperation {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /*
    Boolean add(K key, V value, double score);
    ����һ�����򼯺ϣ����ڵĻ�Ϊfalse�������ڵĻ�Ϊtrue
     */
    @Test
    public void testAdd(){
        System.out.println(redisTemplate.opsForZSet().add("zSet", "zSet-1", 1.0));
    }

    /*
     Long add(K key, Set<TypedTuple<V>> tuples);
     ����һ�����򼯺�
     [zset-1, zset-2, zset-3, zset-4, zset-5, zset-6]
     */

    @Test
    public void testAddTuple(){
        ZSetOperations.TypedTuple<Object> objectTypedTuple1 = new DefaultTypedTuple<>("zset-1",9.1);
        ZSetOperations.TypedTuple<Object> objectTypedTuple2 = new DefaultTypedTuple<>("zset-2",9.2);
        ZSetOperations.TypedTuple<Object> objectTypedTuple3 = new DefaultTypedTuple<>("zset-3",9.3);
        ZSetOperations.TypedTuple<Object> objectTypedTuple4 = new DefaultTypedTuple<>("zset-4",9.5);
        ZSetOperations.TypedTuple<Object> objectTypedTuple5 = new DefaultTypedTuple<>("zset-5",9.6);
        ZSetOperations.TypedTuple<Object> objectTypedTuple6 = new DefaultTypedTuple<>("zset-6",9.9);
        Set<ZSetOperations.TypedTuple<Object>> tuples = new HashSet<ZSetOperations.TypedTuple<Object>>();
        tuples.add(objectTypedTuple1);
        tuples.add(objectTypedTuple2);
        tuples.add(objectTypedTuple3);
        tuples.add(objectTypedTuple4);
        tuples.add(objectTypedTuple5);
        tuples.add(objectTypedTuple6);
        System.out.println(redisTemplate.opsForZSet().add("zset1",tuples));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        /*
        Long remove(K key, Object... values);
        �����򼯺����Ƴ�һ�����߶��Ԫ��
         */
        System.out.println(redisTemplate.opsForZSet().remove("zset1","zset-6"));
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));

        /*
         Long rank(K key, Object o);
         ����������ָ����Ա���������������򼯳�Ա������ֵ����(��С����)˳������
         */
        System.out.println(redisTemplate.opsForZSet().range("zset1",0,-1));
        System.out.println(redisTemplate.opsForZSet().rank("zset1","zset-2"));

        /*
        Long count(K key, double min, double max);
        ͨ�������������򼯺�ָ�������ڵĳ�Ա����
        ������Ϊ��
        [zset-2, zset-1, zset-3]
        3
         */
        System.out.println(redisTemplate.opsForZSet().rangeByScore("zset1",0,5));
        System.out.println(redisTemplate.opsForZSet().count("zset1",0,5));

        /*
        Long size(K key);
        ��ȡ���򼯺ϵĳ�Ա�����ڲ����õľ���zCard����
         */

        System.out.println(redisTemplate.opsForZSet().size("zset1"));

        /*
        Double score(K key, Object o);
        ��ȡָ����Ա��scoreֵ
         */
        System.out.println(redisTemplate.opsForZSet().score("zset1","zset-1"));


    }
        /*
        Long removeRange(K key, long start, long end);
        �Ƴ�ָ������λ�õĳ�Ա���������򼯳�Ա������ֵ����(��С����)˳������
         */
    @Test
    public void TestRemoveRange(){
        //����һ�����򼯺�
        redisTemplate.opsForZSet().add("sortSet","score1",1.0);
        redisTemplate.opsForZSet().add("sortSet","score2",1.1);
        redisTemplate.opsForZSet().add("sortSet","score3",1.2);
        redisTemplate.opsForZSet().add("sortSet","score4",1.3);
        redisTemplate.opsForZSet().add("sortSet","score5",1.4);
        redisTemplate.opsForZSet().range("sortSet",0,-1);
        System.out.println(redisTemplate.opsForZSet().removeRange("sortSet",1,2));
        System.out.println(redisTemplate.opsForZSet().range("sortSet",0,-1));
    }
    @Test
    public void TestIterator(){
        Cursor<ZSetOperations.TypedTuple<Object>> cursor = redisTemplate.opsForZSet().scan("sortSet", ScanOptions.NONE);
        while (cursor.hasNext()){
            ZSetOperations.TypedTuple<Object> item = cursor.next();
            System.out.println(item.getValue() + ":" + item.getScore());
        }

    }

}
