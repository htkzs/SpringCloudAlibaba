package com.itheima.provider.cntroller;

import com.itheima.provider.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class RocketMqProviderController {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    public Order createOrder(){
        Order order = new Order(1,"张三","123123","软件园",new Date());
        rocketMQTemplate.convertAndSend("orderTopic",order);
        return order;
    }
}
