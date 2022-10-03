package com.itheima.provider.configuration;


//import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
//import org.apache.commons.lang.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//// Sentinel提供了 RequestOriginParser 接口来处理访问来源，Sentinel保护的资源如果被访问，就会调用 RequestOriginParser解析访问来源。
//public class RequestOriginParserDefinition implements RequestOriginParser {
//    @Override
//    public String parseOrigin(HttpServletRequest httpServletRequest) {
//        String name = httpServletRequest.getParameter("name");
//        if(StringUtils.isEmpty(name)){
//            throw new RuntimeException("name is null");
//        }
//        return name;
//    }
//}
