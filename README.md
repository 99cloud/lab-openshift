# lab-openshift

## SpringCloud

1. 首先在 Amnibus-CaaS 平台上使用 SpringCloud-Eureka 模板创建服务发现与注册组件 Eureka，`Eureka Route HostName` 建议按 eureka.${apps_dns_domain} 规则填写。

2. 测试 Eureka 服务是否正常：

    ```bash
    $ curl https://eureka.${apps_dns_domain}/health
    {"description":"Composite Discovery Client","status":"UP"}
    ```

3. 创建服务提供者 microservice-provider-user

    `eureka.client.serviceUrl.defaultZone` 需要对应已经创建好的 Eureka 服务，即 `https://eureka.${apps_dns_domain}/eureka`

    ```bash
    $ oc apply -f microservice_provider_user_configmap.yaml
    $ oc apply -f microservice_provider_user_deployment.yaml
    $ oc scale deployment microservice-provider-user-deployment --replicas=3
    ```

4. 创建服务消费者 microservice-consumer-movie

    同样 `eureka.client.serviceUrl.defaultZone` 需要对应已经创建好的 Eureka 服务，即 `https://eureka.${apps_dns_domain}/eureka`

    ```bash
    $ oc apply -f microservice_consumer_movie_configmap.yaml
    $ oc apply -f microservice_consumer_movie_deployment.yaml
    $ oc apply -f microservice_consumer_movie_service.yaml
    ```

5. 测试消费者是否成功从 Eureka 获取服务提供者

    ```bash
    $ curl http://microservice-consumer-movie.default.svc.cluster.local/user/1
    {"id":1,"username":"account1","name":"张三","age":20,"balance":100.00}
    $ curl http://microservice-consumer-movie.default.svc.cluster.local/user/2
    {"id":2,"username":"account2","name":"李四","age":28,"balance":180.00}
    $ curl http://microservice-consumer-movie.default.svc.cluster.local/user/3
    {"id":3,"username":"account3","name":"王五","age":32,"balance":280.00}
    ```
