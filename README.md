# neo-http
neo-http 除了是一套基于Http协议进行封装的远程调用组件，更是一套约束规范。它一方面给出了 RESTful API 设计规范；另一方面统一了响应结果与异常处理。详细请参阅[RESTful API 规范]()

## 特性
- 统一 Http 通讯结构；
- 支持接口签名机制；
- 支持 Endpoint 签名认证；
- 支持提供 RequestId；
- 规范异常处理，避免服务内部异常返回给请求方。

## Get Started
以下简易教程将引导你基于 Spring Boot 和 neo-http-server 完成构建 Web 服务。如需要更详尽的指引，请查阅[用户手册]()获取更多关于 neo-http 的内容。

- [构建 Web 服务](https://github.com/305983806/neo-http/wiki/%E6%9E%84%E5%BB%BA-Web-%E6%9C%8D%E5%8A%A1)    
学习如何利用 neo-http-server 构建一个规范化和带有统一响应结果与异常处理的 RESTful web service，并为服务增加请求签名机制。

- [构建 web 请求客户端](https://github.com/305983806/neo-http/wiki/%E6%9E%84%E5%BB%BA-web-%E8%AF%B7%E6%B1%82%E5%AE%A2%E6%88%B7%E7%AB%AF)
学习如何利用 neo-http-client 构建一个 Http 请求客户端，和如何配合服务端的请求签名机制，实现请求加签。
