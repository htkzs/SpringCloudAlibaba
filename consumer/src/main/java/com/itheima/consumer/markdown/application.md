spring:
application:
name: consumer
cloud:
nacos:
discovery:
server-addr: 192.168.26.1:8848
server:
port: 9002

# ribbon属于客户端的负载均衡
# 随机策略
provider:
ribbon:
NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
# 权重策略配置
#provider:
#  ribbon:
#    NFLoadBalancerRuleClassName: com.itheima.consumer.strategy.NacosWeightedRule

#rocketmq:
#  name-server: 192.168.0.102:9876