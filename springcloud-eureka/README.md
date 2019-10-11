# Eureka

Eureka 是 Netflix 公司开源的一款服务发现组件，该组件提供的服务发现可以为负载均衡、failover 提供支持。Eureka 最初针对 AWS 不提供中间服务层的负载均衡的限制而设计开发的。AWS Elastic Load Balancer 用来对客户端或终端设备请求进行负载均衡，而 Eureka 则用来对中间层的服务做服务发现，配合其他组件提供负载均衡的能力。

构建 99cloud/eureka 镜像：

```bash
$ mvn package
```

在本地启动 Eureka 服务：

```bash
$ docker run --name eureka -p 8761:8761 -d 99cloud/eureka:latest
```

打开 [http://localhost:8761](http://localhost:8761) 可以查看 Eureka Dashboard

检查 Eureka 服务是否正常：

```bash
$ curl http://localhost:8761/health
{"description":"Composite Discovery Client","status":"UP"}
```

## Eureka REST API

[https://github.com/Netflix/eureka/wiki/Eureka-REST-operations](https://github.com/Netflix/eureka/wiki/Eureka-REST-operations)

| 操作 | HTTP 请求 | 描述 |
| --- | --- | --- |
| 注册新的应用实例 | POST /eureka/apps/**{appID}** | 可以输入 JSON 或 XML 格式的请求体，成功返回 204 |
| 注销应用实例 | DELETE /eureka/apps/**{appID}**/**{instanceID}** | 成功返回 200 |
| 应用实例发送心跳 | PUT /eureka/apps/**{appID}**/**{instanceID}** | 成功返回 200，如果 instanceID 不存在返回 404 |
| 查询所有实例 | GET /eureka/apps | 成功返回 200，输出 JSON 或 XML 格式 |
| 查询指定 appID 的实例 | GET /eureka/apps/**{appID}** | 成功返回 200，输出 JSON 或 XML 格式 |
| 根据指定 appID 和 instanceID 查询 | GET /eureka/apps/**{appID}**/**{instanceID}** | 成功返回 200，输出 JSON 或 XML 格式 |
| 根据指定 instanceID 查询 | GET /eureka/instances/**instanceID** | 成功返回 200，输出 JSON 或 XML 格式 |
| 暂停应用实例 | PUT /eureka/apps/**{appID}**/**{instanceID}**/status?value=OUT_OF_SERVICE | 成功返回 200，失败返回 500 |
| 恢复应用实例 | DELETE /eureka/apps/**{appID}**/**{instanceID}**/status?value=UP | 成功返回 200，失败返回 500 |
| 更新元数据 | PUT /eureka/apps/**{appID}**/**{instanceID}**/metadata?key=value | 成功返回 200，失败返回 500 |
| 根据 vip 地址查询 | GET /eureka/vips/**{vipAddress}** | 成功返回 200，输出 JSON 或 XML 格式 |
| 根据 svip 地址查询 | GET /eureka/svips/**{svipAddress}** | 成功返回 200，输出 JSON 或 XML 格式 |
