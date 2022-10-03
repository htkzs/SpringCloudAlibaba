package com.itheima.consumer.service;

import com.itheima.consumer.entity.Order;
import com.itheima.consumer.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class OrderService {
    @Autowired
    private ProviderFeign providerFeign;
    //远程调用provider服务
    public Order createOrder(int orderId){
        Order order = providerFeign.createOrder(orderId);
        return order;

    }

}
