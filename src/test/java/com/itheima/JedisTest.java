package com.itheima;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import sun.awt.windows.WPrinterJob;

@RunWith(SpringRunner.class)
@SpringBootTest
class JedisTest {
    @Test
    public void jedisConnection(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(20);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(5);

        JedisPool jedisPool = new JedisPool(jedisPoolConfig,"192.168.0.5",6379,3000,null);
        Jedis jedisPoolResource = null;
        ////从redis连接池里拿出一个连接执行命令
        try{
            jedisPoolResource = jedisPool.getResource();
            System.out.println(jedisPoolResource.set("tuling","666"));
            System.out.println(jedisPoolResource.get("tuling"));
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            jedisPoolResource.close();
        }



    }
}
