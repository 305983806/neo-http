# neo-http
neo-http 除了是一套基于Http协议进行封装的远程调用组件，更是一套约束规范。它一方面给出了 RESTful API 设计规范；另一方面统一了响应结果与异常处理。

## 特性
- 统一 Http 通讯结构：通过定义一套既定的Http请求/响应
- 支持接口签名机制：
- 支持 Endpoint 签名认证：
- 近乎本地调用的开发体验：
- 轻松作出业务异常提示.

## Get Started
以下简易教程将引导你基于 Spring Boot 和 neo-http-server 完成构建 Web 服务。如需要更详尽的指引，请查阅[用户手册]()获取更多关于 neo-http 的内容。

- [构建标准化 RESTful Web 服务]()    
学习如何利用 neo-http-server 构建一个规范化和带有统一响应结果与异常处理的 RESTful web service。

- [构建标准化 RESTful Web 请求客户端]()    
学习如何利用 neo-http-client 构建一个 Http 请求客户端。

整体引入
```groovy
compile 'com.neo:neo-http:0.1.0'
```

仅引入服务端
```groovy
compile 'com.neo:neo-http-server:0.1.0'
```

仅引入客户端
```groovy
compile 'com.neo:neo-http-client:0.1.0'
```

