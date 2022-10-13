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
    ���򼯺������Ԫ�أ�������Ӹ���
    Ҳ����ֱ����add������Ӷ��ֵ �磺template.opsForSet().add("setTest","aaa","bbb") ����ֵΪ���Ԫ�صĸ���

     */
    @Test
    public void testSet(){
        String[] str = new String[]{"ccc","ddd"};
        //����ֵΪ���Ԫ�صĸ���
        System.out.println(redisTemplate.opsForSet().add("list", "aaa", "bbb"));
        System.out.println(redisTemplate.opsForSet().add("list", str));

        /*
         Long remove(K key, Object... values);
         �Ƴ�������һ��������Ա
         */
        System.out.println(redisTemplate.opsForSet().remove("list", str));
    }

    /*
    V pop(K key);
    �Ƴ������ؼ����е�һ�����Ԫ��
     */
    @Test
    public void testPOP(){
        redisTemplate.opsForSet().add("fruit:key","peach","cherry","melon","lemon");
        System.out.println(redisTemplate.opsForSet().pop("fruit:key"));
        System.out.println(redisTemplate.opsForSet().members("fruit:key"));
        /*
         Long size(K key);
         ���򼯺ϵĴ�С����
         */
        System.out.println("���򼯺ϵĴ�С����:"+redisTemplate.opsForSet().size("fruit:key"));
    }
    /*
    Boolean move(K key, V value, K destKey);
    �� member Ԫ�ش� source �����ƶ��� destination ����
     */
    @Test
    public void TestMove(){
        redisTemplate.opsForSet().add("country","china","america","japan","Australia","India");
        //��һ�����򼯺��е�Ԫ���ƶ���һ�������ڵ�key�лᷢ��ʲô ��֤���ֶ��ڲ����ڵ�key���Զ�����
        redisTemplate.opsForSet().move("country","china","destination");
    }
    /*
     Cursor<V> scan(K key, ScanOptions options);
     ����set
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
