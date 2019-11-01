# neo-http
neo-http 除了是一套基于Http协议进行封装的远程调用组件，更是一套约束规范。它一方面给出了 Restful API 设计规范；另一方面统一了响应结果与异常处理。

## 特性
- 统一 Http 通讯结构：通过定义一套既定的Http请求/响应
- 支持接口签名机制：
- 支持 Endpoint 签名认证：
- 近乎本地调用的开发体验：
- 轻松作出业务异常提示.

## 用户文档
- [概述]()
- [Restful API 设计规范]()
- 

## Get Started
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