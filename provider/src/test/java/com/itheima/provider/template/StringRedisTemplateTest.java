package com.itheima.provider.template;

import com.alibaba.fastjson.JSON;
import com.itheima.provider.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
//ͨ����ע����Ի�ȡ��Spring��������
@RunWith(SpringRunner.class)
public class StringRedisTemplateTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisTemplate(){
        stringRedisTemplate.opsForValue().set("name","Alibaba");
        String name = (String)stringRedisTemplate.opsForValue().get("name");
        System.out.println(name);
    }
    /*
    ������ Demo �����н�����Կ�����Ϊ���ڷ����л�ʱ֪����������ͣ�Json ���л��Ὣ��� class ����д�� json ����д��� Redis�������������ڴ濪��
    Ϊ�˽�ʡ�ڴ�ռ䣬���ǲ�����ʹ�� json ���л��������� value������ͳһʹ�� String ���л�����Ҫ��ֻ�ܴ洢 String ���͵� key �� value������Ҫ�洢 Java ����ʱ���ֶ���ɶ�������л��ͷ����л�
     */
    @Test
    public void testSaveUser() {
        User user = new User("С��", 23);
        // ���л�
        stringRedisTemplate.opsForValue().set("user", JSON.toJSONString(user));
        String user1 = stringRedisTemplate.opsForValue().get("user");
        // �����л�
        User user2 = JSON.parseObject(user1, User.class);
        System.out.println(user2);
    }

}
