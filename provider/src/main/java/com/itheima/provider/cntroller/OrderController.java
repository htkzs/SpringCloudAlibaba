package com.itheima.provider.cntroller;

import com.itheima.provider.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/create")
public class OrderController {
    @RequestMapping("/order/{orderId}")
    public Order createOrder(@PathVariable int orderId){
        System.out.println("订单id为"+orderId);
        return new Order(1001,"zhangsan","13991742014","北京",new Date());
    }
}
