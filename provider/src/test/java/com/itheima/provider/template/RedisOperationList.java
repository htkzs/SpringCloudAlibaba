package com.itheima.provider.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RedisOperationList {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RedisOperationList.class);
    /*
    Long size(K key);
    返回存储在键中的列表的长度。如果键不存在，则将其解释为空列表，并返回0。当key存储的值不是列表时返回错误
     */
    @Test
    public void testListSize(){

        Long all = redisTemplate.opsForList().leftPushAll("fruit", "apple", "pear", "banana", "grape");
        System.out.println("插入的元素个数为"+all);
        System.out.println(redisTemplate.opsForList().size("fruit"));
    }
    /*
    Long leftPush(K key, V value);
    将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从左边插入）
     */
    @Test
    public void testLeftPush(){
        // 返回的结果为推送操作后的列表的长度
        Long len = redisTemplate.opsForList().leftPush("fruit", "orange");
        System.out.println("推送操作后的列表的长度为:"+len);
        System.out.println(redisTemplate.opsForList().range("fruit",0,4));
    }
    /*
    Long leftPushAll(K key, V... values);
    批量把一个数组插入到列表中
     */
    @Test
    public void testLeftPushAll(){
        String[] array = new String[]{"peach","cherry","melon","lemon"};
        Long len = redisTemplate.opsForList().leftPushAll("fruit", array);
        System.out.println("推送操作后的列表的长度为:"+len);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    Long rightPush(K key, V value);
    将所有指定的值插入存储在键的列表的头部。如果键不存在，则在执行推送操作之前将其创建为空列表。（从右边插入）
     */
    @Test
    public void testRightPush(){
        Long offset = redisTemplate.opsForList().rightPush("fruit", "pineapple");
        System.out.println("推送操作后的列表的长度为:"+offset);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    Long rightPushAll(K key, V... values);
     */
    @Test
    public void testRightPushAll(){
        String[] list = new String[]{"lichee","longan","plum"};
        Long length = redisTemplate.opsForList().leftPushAll("fruit", list);
        System.out.println("推送操作后的列表的长度为:"+length);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    void set(K key, long index, V value);
    在列表中index的位置设置value值
     */
    @Test
    public void testSetIndex(){
        System.out.println(redisTemplate.opsForList().range("fruit", 0, -1));
        redisTemplate.opsForList().set("fruit",1,"hawthorn");
        System.out.println("修改后的列表:"+redisTemplate.opsForList().range("fruit", 0, -1));
    }
    /*
    Long remove(K key, long count, Object value);
    从存储在键中的列表中删除等于值的元素的第一个计数事件。
    计数参数以下列方式影响操作：
    count> 0：删除等于从头到尾移动的值的元素。
    count <0：删除等于从尾到头移动的值的元素。
    count = 0：删除等于value的所有元素。

     */
    @Test
    public void testRemove(){
        String[] str = new String[]{"a","b","c","d"};
        Long len = redisTemplate.opsForList().rightPushAll("list", str);
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        redisTemplate.opsForList().remove("list",1,"b");
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
    }

    /*
    V index(K key, long index);
    根据下标获取列表中的值，下标是从0开始的
     */
    @Test
    public void testIndex(){
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        String value = (String)redisTemplate.opsForList().index("list", 2);
        System.out.println(value);
    }
   /*
   V leftPop(K key);
   弹出最左边的元素，弹出之后该值在列表中将不复存在
   V rightPop(K key);
   弹出最右边的元素，弹出之后该值在列表中将不复存在
    */
    public void testPop(){
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().rightPop("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        logger.info(redisTemplate.opsForList().range("list", 0, -1)+"");
    }

}
