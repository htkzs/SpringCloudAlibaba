package com.itheima.provider.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SetOperation {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    /*
    Long add(K key, V... values);
    无序集合中添加元素，返回添加个数
    也可以直接在add里面添加多个值 如：template.opsForSet().add("setTest","aaa","bbb") 返回值为添加元素的个数

     */
    @Test
    public void testSet(){
        String[] str = new String[]{"ccc","ddd"};
        //返回值为添加元素的个数
        System.out.println(redisTemplate.opsForSet().add("list", "aaa", "bbb"));
        System.out.println(redisTemplate.opsForSet().add("list", str));

        /*
         Long remove(K key, Object... values);
         移除集合中一个或多个成员
         */
        System.out.println(redisTemplate.opsForSet().remove("list", str));
    }

    /*
    V pop(K key);
    移除并返回集合中的一个随机元素
     */
    @Test
    public void testPOP(){
        redisTemplate.opsForSet().add("fruit:key","peach","cherry","melon","lemon");
        System.out.println(redisTemplate.opsForSet().pop("fruit:key"));
        System.out.println(redisTemplate.opsForSet().members("fruit:key"));
        /*
         Long size(K key);
         无序集合的大小长度
         */
        System.out.println("无序集合的大小长度:"+redisTemplate.opsForSet().size("fruit:key"));
    }
    /*
    Boolean move(K key, V value, K destKey);
    将 member 元素从 source 集合移动到 destination 集合
     */
    @Test
    public void TestMove(){
        redisTemplate.opsForSet().add("country","china","america","japan","Australia","India");
        //将一个无序集合中的元素移动到一个不存在的key中会发生什么 验证发现对于不存在的key会自动创建
        redisTemplate.opsForSet().move("country","china","destination");
    }
    /*
     Cursor<V> scan(K key, ScanOptions options);
     遍历set
     */
    @Test
    public void TestIterator(){
        redisTemplate.opsForSet().add("month","January","February","March","April","May","June");
        Cursor<Object> month = redisTemplate.opsForSet().scan("month", ScanOptions.NONE);
        while(month.hasNext()){
            System.out.println(month.next());
        }
    }

}
