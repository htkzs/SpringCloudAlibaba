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
//通过该注解可以获取到Spring容器对象
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
    从上述 Demo 的运行结果可以看到，为了在反序列化时知道对象的类型，Json 序列化会将类的 class 类型写入 json 结果中存入 Redis，会带来额外的内存开销
    为了节省内存空间，我们并不会使用 json 序列化器来处理 value，而是统一使用 String 序列化器，要求只能存储 String 类型的 key 和 value。当需要存储 Java 对象时，手动完成对象的序列化和反序列化
     */
    @Test
    public void testSaveUser() {
        User user = new User("小白", 23);
        // 序列化
        stringRedisTemplate.opsForValue().set("user", JSON.toJSONString(user));
        String user1 = stringRedisTemplate.opsForValue().get("user");
        // 反序列化
        User user2 = JSON.parseObject(user1, User.class);
        System.out.println(user2);
    }

}
