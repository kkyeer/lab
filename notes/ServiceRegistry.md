# 分布式服务中的服务注册、发现与注册中心可视化

[toc]

## 1. 概述

当前Spring体系下的分布式服务体系基本上集中于最早由Netflix框架开源的SpringCloud体系和阿里巴巴开源的Dubbo体系，对于微服务化后的服务间相互调用，SpringCloud定义了多种组件，来保证调用链路的高可用，并且方便快捷开发，典型的如专注于服务注册与发现的Eureka，专注于Api调用与序列化过程的Feign，专注于负载均衡的Ribbon（目前此项目处于维护阶段，不建议使用）等组件,相应的在Dubbo体系中也有相关的类似方案，部分实践中也会结合Dubbo与SpringCloud来综合利用两者的优点，
本文主要研究各种不同的服务注册方式与注册中心的细节对比。

## 2. 服务注册

服务注册主要参与者为服务注册客户端及注册中心，常用的为EurekaClient+EurekaServer,Dubbo+Zookeeper,Dubbo+Nacos等组合，另外SpringCloud和Dubbo社区也有互相搭配其他注册中心的项目(SpringCloud+Zookeeper,Dubbo+Redis,SofaRegistry作为注册中心等)

### 2.1 注册粒度

- EurekaServer：Application

    仅支持配置配置ApplicationName，服务发现也是Application级别

    ```properties
    server:
    port: 39090

    spring:
    application:
        name: UserService
    ```

- Dubbo+Zookeeper：Application+Service+版本

    Dubbo的注册和调用单位为Application+Service+Version,其中Version可选。

    ```yml
    dubbo:
        application:
            name: user-service
        registry:
            address: zookeeper://localhost:2181
        protocol:
            name: dubbo
            port: 20880
    ```

    ```xml
    <dubbo:service interface="org.apache.dubbo.samples.basic.api.DemoService" ref="demoService" version="1.0"/>
    ```

- Dubbo+Nacos：Application+Service+版本
    使用Nacos作为Dubbo的注册中心，只需要将原来的Zookeeper注册中心配置替换成Nacos即可

    ```yml
    dubbo:
        #注册中心配置，用于配置连接注册中心相关信息
        registry:
            address: nacos://localhost:8848
    ```

### 2.2 已注册服务可视化

某些情况下，需要以GUI的形式查看当前已注册的服务，甚至希望通过RestApi对第三方平台开放服务限流等能力，总结多种注册中心的相关可视化及管理框架如下

#### 2.2.1 Eureka Server

EurekaServer原生提供简陋的GUI工具，可以查看注册的服务，Eureka节点的综合状态等，但未提供OpenApi，也未提供第三方管理功能

![EurekaAdmin.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/EurekaAdmin.png)

#### 2.2.2 Dubbo+Zookeeper

Dubbo官方提供了DubboAdmin工具包([GitHub地址](https://github.com/apache/dubbo-admin))，具备了服务查询，服务治理(包括Dubbo2.7中新增的治理规则)以及服务测试三部分内容
![DubboAdminList.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/DubboAdminList.png)
![DubboAdminDetail.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/DubboAdminDetail.png)

#### 2.2.3 Dubbo+Nacos

Nacos自带一套完备的控制台：
![NacosAdmin.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/NacosAdmin.png)
![NacosServiceDetail.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/NacosServiceDetail.png)
同时Nacos提供完备的OpenApi([官网说明](https://nacos.io/zh-cn/docs/open-api.html))，支持服务实例的增删改查，以及高级的健康状态查询功能

![NacosOpenApi.png](https://cdn.jsdelivr.net/gh/kkyeer/picbed/NacosOpenApi.png)

#### 2.2.4 其他

实际实践中，开源的服务注册与发现方式并不一定符合公司的要求，因此业界不断在涌现一些新的轮子，如Redis作为注册中心，蚂蚁金服开源的SofaRegistry(不完善)，SpringCloudZookeeper，但是这些方案或者不成熟，或者需要复杂的运维工作，不太适合小规模的微服务开发，不在此讨论

#### 2.2.5 各方案对比

| 注册方式                | 注册粒度                      | GUI查看注册服务   | GUI管理注册服务 | RestAPI |
|---------------------|---------------------------|-------------|-----------|---------|
| SpringCloud\+Eureka | App                       | √自带         | X         | X       |
| Dubbo\+Zookeeper    | App+Service[+Version] | √DubboAdmin | X         | X       |
| Dubbo\+Nacos        | App+Service[+Version] | √自带         | √自带       | √自带     |

就服务注册与发现维度来说，SpringCloud(及衍生方案)有先天的局限性，如粒度高，无法做到精细控制，由于缺少版本号概念，因此热更新、灰度更新需要额外的工作，反之Dubbo在此之上开源了一大部分的能力，搭配自带完备GUI+RestApi的Nacos注册中心，可以最大程度上解决微服务化在服务注册上的需求，当然，Nacos还有一个额外的优势，其作为阿里云提供的收费业务之一，相对Netflix主操刀的Eureka和DubboAdmin，理论上后续迭代稍微有保证，当然由于局限于国内的技术圈子使用，技术实例和实践上的稳定性还有待观察。
