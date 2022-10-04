package com.itheima.provider.cntroller;

import com.itheima.provider.Order;
import com.itheima.provider.entity.OrderInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/create")
@Slf4j
public class OrderController {
    @RequestMapping("/order/{orderId}")
    public Order createOrder(@PathVariable int orderId){
        System.out.println("订单id为"+orderId);
        return new Order(1001,"zhangsan","13991742014","北京",new Date());
    }
    //传递一个Order对象返回一个OrderInfo对象
    @RequestMapping("/orderInfo")
    public OrderInfo createOrderInfo(@RequestBody Order order){
        System.out.println(order);
        log.info("远程传递的参数order:{}",order);
        return new OrderInfo(1002,"lisi","17371167564","上海",new Date(),new Date());
    }
}
