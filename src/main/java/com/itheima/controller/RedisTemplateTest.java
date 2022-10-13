package com.itheima.controller;

import com.itheima.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//@Controller
//@RequestMapping("/test")
//public class RedisTemplateTest {
//
//    @Autowired
//    RedisUtil redisUtil;
//
//    @PostMapping("/redis")
//    @ResponseBody
//    public AjaxResult testRedis(){
//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        //往redis set值
//        redisUtil.set("test:"+ DateUtils.getDate(),list,24*60*60);
//        return AjaxResult.success();
//    }
//
//    @PostMapping("/getRedis")
//    @ResponseBody
//    public AjaxResult getRedis(){
//        String key = "test:"+ DateUtils.getDate();
//        if (redisUtil.hasKey(key)){
//            //根据key取值
//            return AjaxResult.success( redisUtil.get(key));
//        }
//        return AjaxResult.error();
//    }
//}

