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
    ���ش洢�ڼ��е��б�ĳ��ȡ�����������ڣ��������Ϊ���б�������0����key�洢��ֵ�����б�ʱ���ش���
     */
    @Test
    public void testListSize(){

        Long all = redisTemplate.opsForList().leftPushAll("fruit", "apple", "pear", "banana", "grape");
        System.out.println("�����Ԫ�ظ���Ϊ"+all);
        System.out.println(redisTemplate.opsForList().size("fruit"));
    }
    /*
    Long leftPush(K key, V value);
    ������ָ����ֵ����洢�ڼ����б��ͷ��������������ڣ�����ִ�����Ͳ���֮ǰ���䴴��Ϊ���б�������߲��룩
     */
    @Test
    public void testLeftPush(){
        // ���صĽ��Ϊ���Ͳ�������б�ĳ���
        Long len = redisTemplate.opsForList().leftPush("fruit", "orange");
        System.out.println("���Ͳ�������б�ĳ���Ϊ:"+len);
        System.out.println(redisTemplate.opsForList().range("fruit",0,4));
    }
    /*
    Long leftPushAll(K key, V... values);
    ������һ��������뵽�б���
     */
    @Test
    public void testLeftPushAll(){
        String[] array = new String[]{"peach","cherry","melon","lemon"};
        Long len = redisTemplate.opsForList().leftPushAll("fruit", array);
        System.out.println("���Ͳ�������б�ĳ���Ϊ:"+len);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    Long rightPush(K key, V value);
    ������ָ����ֵ����洢�ڼ����б��ͷ��������������ڣ�����ִ�����Ͳ���֮ǰ���䴴��Ϊ���б������ұ߲��룩
     */
    @Test
    public void testRightPush(){
        Long offset = redisTemplate.opsForList().rightPush("fruit", "pineapple");
        System.out.println("���Ͳ�������б�ĳ���Ϊ:"+offset);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    Long rightPushAll(K key, V... values);
     */
    @Test
    public void testRightPushAll(){
        String[] list = new String[]{"lichee","longan","plum"};
        Long length = redisTemplate.opsForList().leftPushAll("fruit", list);
        System.out.println("���Ͳ�������б�ĳ���Ϊ:"+length);
        System.out.println(redisTemplate.opsForList().range("fruit",0,-1));
    }
    /*
    void set(K key, long index, V value);
    ���б���index��λ������valueֵ
     */
    @Test
    public void testSetIndex(){
        System.out.println(redisTemplate.opsForList().range("fruit", 0, -1));
        redisTemplate.opsForList().set("fruit",1,"hawthorn");
        System.out.println("�޸ĺ���б�:"+redisTemplate.opsForList().range("fruit", 0, -1));
    }
    /*
    Long remove(K key, long count, Object value);
    �Ӵ洢�ڼ��е��б���ɾ������ֵ��Ԫ�صĵ�һ�������¼���
    �������������з�ʽӰ�������
    count> 0��ɾ�����ڴ�ͷ��β�ƶ���ֵ��Ԫ�ء�
    count <0��ɾ�����ڴ�β��ͷ�ƶ���ֵ��Ԫ�ء�
    count = 0��ɾ������value������Ԫ�ء�

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
    �����±��ȡ�б��е�ֵ���±��Ǵ�0��ʼ��
     */
    @Test
    public void testIndex(){
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        String value = (String)redisTemplate.opsForList().index("list", 2);
        System.out.println(value);
    }
   /*
   V leftPop(K key);
   ��������ߵ�Ԫ�أ�����֮���ֵ���б��н���������
   V rightPop(K key);
   �������ұߵ�Ԫ�أ�����֮���ֵ���б��н���������
    */
    public void testPop(){
        System.out.println(redisTemplate.opsForList().leftPop("list"));
        System.out.println(redisTemplate.opsForList().rightPop("list"));
        System.out.println(redisTemplate.opsForList().range("list", 0, -1));
        logger.info(redisTemplate.opsForList().range("list", 0, -1)+"");
    }

}
