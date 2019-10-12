# microservice-provider-user

microservice-provider-user 是用户微服务注册到 Eureka 上的 demo 应用程序。

构建 99cloud/microservice-provider-user 镜像：

```bash
$ mvn package
```

在本地启动 microservice-provider-user 服务：

```bash
$ docker run --name provider -p 8000:8000 -v /path/to/application.yml:/etc/microservice-provider-user/application.yml -d 99cloud/microservice-provider-user:latest
```

测试 microservice-provider-user 服务是否正常：

```bash
$ curl -X GET http://localhost:8000/1
{"id":1,"username":"account1","name":"张三","age":20,"balance":100.00}
$ curl -X GET http://localhost:8000/2
{"id":2,"username":"account2","name":"李四","age":28,"balance":180.00}
$ curl -X GET http://localhost:8000/3
{"id":3,"username":"account3","name":"王五","age":32,"balance":280.00}
```

## 实现服务提供者

1. 添加 Eureka Client 依赖

    ```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
    </dependency>
    ```

2. 编写启动类

    ```java
    @EnableDiscoveryClient
    @SpringBootApplication
    public class ProviderUserApplication {
        public static void main(String[] args) {
            SpringApplication.run(ProviderUserApplication.class, args);
        }
    }
    ```

    将用户微服务注册到 Eureka Server 上。

3. 在配置文件中添加

    ```yaml
    server:
      port: 9000
    spring:
      application:
        name: microservice-provider-user
    eureka:
      client:
        serviceUrl:
        defaultZone: http://192.168.0.108:8761/eureka/
      instance:
        prefer-ip-address: true
    ```

    * spring.application.name 用于指定注册到 Eureka Server 上的应用名称
    * eureka.instance.prefer-ip-address 设置为 true，将自己的 IP 注册到 Eureka Server
