package com.itheima.provider.junittest;

import org.springframework.web.client.RestTemplate;

//该测试类用于测试sentinel的关联限流 当访问index的流量超过阈值将对/list的访问进行限流
public class JunitTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            restTemplate.getForObject("http://localhost:9001/index",String.class);
            Thread.sleep(10);
        }
    }
}
