package com.itheima.provider.test;

import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;


class SentinalTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for(int i=0;i<1000;i++){
            //不断访问  //设置每秒进行五次访问 此时通过远程接口 http://localhost:9001/test 访问provider服务的/index接口将会被限制访问 会被限流
            restTemplate.getForObject("http://localhost:9003/list", String.class);
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
