package com.itheima.consumer.controller;

import com.itheima.consumer.entity.Order;
import com.itheima.consumer.feign.ProviderFeign;
import com.itheima.consumer.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//注意这里需要返回一个对象 要使用@RestController 使用@Controller将报404
@RestController
@RequestMapping("/create")
public class OpenFeignController {
    @Autowired
    private OrderService orderService;
    //访问路径为 localhost:9002/create/order?orderId=1
    @RequestMapping("/order")
    public String createOrder(@RequestParam int orderId){
        Order order = orderService.createOrder(orderId);
        return order.toString();

    }

}
