package com.itheima.consumer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@Slf4j
public class InstancesController {
    @Autowired
    private RestTemplate restTemplate;
    //注入DiscoveryClient用于发现Nacos的服务实例
    @Autowired
    private DiscoveryClient discoveryClient;
    @GetMapping("/instances")
    public List<ServiceInstance> getInstances(){
        List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");
        return provider;
    }

    //服务调用
    @GetMapping("/index")
    public String Serviceinstances(){
        //获取所有的服务列表
        List<ServiceInstance> provider = this.discoveryClient.getInstances("provider");
        //随机选择一个服务调用
        int i = ThreadLocalRandom.current().nextInt(provider.size());
        //得到具体的服务实例
        ServiceInstance serviceInstance = provider.get(i);
        //获取该服务的uri
        URI uri = serviceInstance.getUri();
        String url = uri+"/index";
        log.info("调用了端口为{}的服务",serviceInstance.getPort());
        //远程调用provider服务
        return this.restTemplate.getForObject(url,String.class);
    }

    //通过ribbon解析服务名称简化服务的调用
    @GetMapping("/test")
    public String ribboninstances(){
        return restTemplate.getForObject("http://provider/index",String.class);
    }

}
