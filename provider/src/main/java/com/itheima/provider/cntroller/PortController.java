package com.itheima.provider.cntroller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.itheima.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PortController {
    @Value("${server.port}")
    private String port;
    int i =0;
    @GetMapping("/index")
    public String getPort(){
        i++;
        //此时的异常比例为50%
        //if(i%2 == 0) throw new RuntimeException();
        return port;
    }
    @GetMapping("/list")
    public String list(){
        return "list";
    }

    @Autowired
    private ProviderService providerService;
    @GetMapping("/test1")
    public String test1(){
        providerService.test();
        return "test1";
    }

    @GetMapping("/test2")
    public String test2(){
        providerService.test();
        return "test2";
    }
    @GetMapping("/hot")
    @SentinelResource("hot")
    public String hot(@RequestParam(value = "num1",required = false) Integer num1,
                      @RequestParam(value = "num2",required = false) Integer num2){
        return num1+"-"+num2;
    }
}
