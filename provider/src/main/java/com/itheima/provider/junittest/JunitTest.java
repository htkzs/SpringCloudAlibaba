package com.itheima.provider.junittest;

import org.springframework.web.client.RestTemplate;

public class JunitTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            restTemplate.getForObject("http://localhost:9001/index",String.class);
            Thread.sleep(10);
        }
    }
}
