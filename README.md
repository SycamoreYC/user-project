- [X] 使用(Spring initializr)[https://start.spring.io/] 初始化项目，使用Maven/Gradle进行构建。
- [X] 创建REST API（User API），实现对User的CRUD。User对象需要包含以下属性：id, name, age, createdAt, updatedAt等。
- [X] 使用MySQL/PostgreSQL进行数据持久化，使用Flyway/Liquibase进行数据库版本控制。
- [X] 使用Junit5和Mockito编写单元测试，除配置类外测试覆盖率100%。使用jacoco进行测试覆盖率检查。
- [X] 需要实现分页查询功能。
- [X] 需要实现动态查询，比如根据姓名和年龄或者创建时间区间进行查询。
- [X] 创建用户时，id、createdAt和updatedAt需要自动生成。
- [X] 使用Docker搭建本地运行环境, 本地Docker运行环境中使用不同profile。

- [X] 创建第二个REST API（Email API），它根据 User 的ID，返回其邮箱地址。规则：如果id为 12345，那么邮箱地址为 12345@rest.local，即添加后缀即可。(只是简单的实现)
- [X] 给 User API 和 Email 添加 actuator。
- [X] 创建一个 Eureka Server 项目并启动。
- [X] 把 User API 和 Email API 作为客户端，注册到 Eureka Server上。
- [X] 在 UserAPI 中添加或者修改返回用户详情的API，使其返回来自 Email API的邮箱地址。User API中，需要使用 Feign, Ribbon和Eureka Client进行负载均衡。
- [X] 在User API中使用 Hystrix，在调用Email API的部分添加断路器和fallback响应。访问 User API，同时手动关闭和打开 Email API，查看断路器的行为。
- [X] 使用 Spring Cloud Gateway 对外暴露 User API和Email API。
- [ ] 使用 Spring Cloud Config 管理 User API的配置。

## What
User Api includes CRUD

## How to start

### local
```
docker-compose up
```
The server will be running at http://localhost:8080

## APIS
1. save
```
url: /users
methdo: POST
body: { name: "test", age: 18 }
```

2. delete
```
ur: /users/:userId
method: DELETE
```

3. update
```
url: /users/:userId
method: PUT
body: { name: "test1", age: 19 }
```

4. get
```
url: /users/:userId
method: GET
```

5. search by age
```
url: /users/age-range
method: GET
queryParams: { upper: 10, lower: 18 }
```

6. search by name
```
url: /users/name-pattern
method: GET
queryParams: { name: "te" }
```