package com.itheima.consumer.service;

import com.itheima.consumer.entity.Order;
import com.itheima.consumer.entity.OrderInfo;
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
    public OrderInfo createOrderInfo(Integer orderId){
        Order order = providerFeign.createOrder(orderId);
        //演示远程调用传递的参数为一个对象 和返回的参数也为一个对象
        return providerFeign.createOrderInfo(order);

    }

}
