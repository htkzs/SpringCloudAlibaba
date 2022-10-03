package com.itheima.consumer.service;

import com.itheima.consumer.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
//@RocketMQMessageListener(consumerGroup = "myConsumer",topic = "orderTopic")
public class RocketMqConsumerService implements RocketMQListener<Order> {
    @Override
    public void onMessage(Order order) {
        log.info("新订单{},发短信",order);
    }
}
