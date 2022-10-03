package com.itheima.provider.configuration;

import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

@Configuration
public class SentinelConfiguration {
    @PostConstruct
    public void init(){

        WebCallbackManager.setRequestOriginParser(new RequestOriginParser() {
            @Override
            public String parseOrigin(HttpServletRequest httpServletRequest) {
                String name = httpServletRequest.getParameter("name");
                if(StringUtils.isEmpty(name)){
                    throw new RuntimeException("name is null");
                }
                return name;
            }
        });
    }
}
