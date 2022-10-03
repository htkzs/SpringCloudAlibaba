package com.itheima.provider;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = ProviderApplicationTests.class)
@RunWith(SpringRunner.class)
public class ProviderApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void rocketmqProviderTest() throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        //创建消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("myproducer-group");
        //设置NameServer
        producer.setNamesrvAddr("192.168.0.102:9876" );
        //启动生产者
        producer.start();
        //构建消息对象
        Message message = new Message("myTopic","myTag",("Test MQ").getBytes());
        //发送消息
        SendResult result = producer.send(message, 10000);
        System.out.println(result);
        //关闭生产者
        producer.shutdown();
    }
}
