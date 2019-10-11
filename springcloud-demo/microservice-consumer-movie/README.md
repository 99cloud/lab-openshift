# microservice-consumer-movie

Feign 是 Netflix 开发的声明式、模板化的 HTTP 客户端，可以帮助我们更快捷、优雅地调用 HTTP API。

github repo: [https://github.com/OpenFeign/feign](https://github.com/OpenFeign/feign)

microservice-provider-user 是调用 microservice-provider-user 提供的 API 获得数据的 demo 应用程序。

构建 99cloud/microservice-consumer-movie 镜像：

```bash
$ mvn package
```

在本地启动 microservice-consumer-movie 服务：

```bash
$ docker run --name movie -p 8010:8010 -v /path/to/application.yml:/etc/microservice-consumer-movie/application.yml -d 99cloud/microservice-consumer-movie:latest
```

**在启动了 eureka 和 microservice-provider-user 后**：

```bash
$ curl -X GET http://localhost:8010/user/1
{"id":1,"username":"account1","name":"张三","age":20,"balance":100.00}
$ curl -X GET http://localhost:8010/user/2
{"id":2,"username":"account2","name":"李四","age":28,"balance":180.00}
$ curl -X GET http://localhost:8010/user/3
{"id":3,"username":"account3","name":"王五","age":32,"balance":280.00}
```

## 为消费服务整合 Feign

1. 添加 Feign 依赖

    ```xml
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-openfeign</artifactId>
    </dependency>
    ```

2. 创建 Feign 接口，并添加 `@FeignClient` 注解

    ```java
    @FeignClient(name = "microservice-provider-user")
    public interface UserFeignClient {
        @RequestMapping(value = "/{id}", method = RequestMethod.GET)
        public User findById(@PathVariable("id") Long id);
    }
    ```

3. 修改 Controller 代码来调用 Feign 接口

    ```java
    @RestController
    public class MovieController {
        @Autowired
        private UserFeignClient userFeignClient;

        @GetMapping("/user/{id}")
        public User findById(@PathVariable Long id) {
            return this.userFeignClient.findById(id);
        }
    }
    ```

4. 修改启动类，为其添加 `@EnableFeignClients` 注解：

    ```java
    @EnableDiscoveryClient
    @SpringBootApplication
    @EnableFeignClients
    public class ConsumerMovieApplication {
        public static void main(String[] args) {
            SpringApplication.run(ConsumerMovieApplication.class, args);
        }
    }
    ```
