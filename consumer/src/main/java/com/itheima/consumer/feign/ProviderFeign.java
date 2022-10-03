package com.itheima.consumer.feign;


import com.itheima.consumer.entity.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("provider")
public interface ProviderFeign {
    @RequestMapping("/create/order/{orderId}")
    public Order createOrder(@PathVariable int orderId);

}
