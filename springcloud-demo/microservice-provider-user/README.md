# microservice-provider-user

microservice-provider-user 是用户微服务注册到 Eureka 上的 demo 应用程序

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
