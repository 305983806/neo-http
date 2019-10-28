package com.neo.http.sample.hello.api;

import com.neo.http.client.AbstractHttpManager;
import com.neo.http.sample.hello.api.impl.HelloServiceImpl;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-28 16:18
 */
public class HelloHttpManager extends AbstractHttpManager {

    private String appName = "hello";

    private HelloService helloService = new HelloServiceImpl(this);

    public HelloHttpManager(String endPoint) {
        super("hello", endPoint);
    }

    public HelloHttpManager(String endPoint, String accessKeyId, String accessKeySecret) {
        super("hello", endPoint, accessKeyId, accessKeySecret);
    }

    public HelloService getHelloService() {
        return this.helloService;
    }

}
