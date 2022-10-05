通过 http://localhost:8888/provider/list网关访问微服务接口时出现如下异常
org.springframework.http.server.reactive.ServerHttpResponse.setRawStatusCode(Ljava/lang/Integer;)Z

解决办法 将boot的版本升级为 2.3.1.RELEASE

如果发生其它组件的不兼容 重新设置为2.2.1.RELEASE

关于gateway配置的说明

port: 8888
spring:
application:
name: gateway
cloud:
gateway:
discovery:
locator:
enabled: true
routes:
- id: provider_route
uri: http://localhost:9003
predicates:
- Path=/provider/**
filters:
- StripPrefix=1
#    nacos:
#      discovery:
#        server-addr: 192.168.26.1:8848