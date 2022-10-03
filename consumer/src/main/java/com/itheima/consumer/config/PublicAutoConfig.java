package com.itheima.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//@Configuration
public class PublicAutoConfig {
    @Bean
    @LoadBalanced
    public RestTemplate getRestTemplete(){
        return new RestTemplate();
    }
}
