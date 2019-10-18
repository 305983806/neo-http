package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.httpservice.JoddHttpService;
import com.neo.http.sample.hello.api.HelloService;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 18:23
 */
public class HelloFactory extends JoddHttpService {

    private HelloService helloService = new HelloServiceImpl(this);

    public HelloService getHelloService() {
        return helloService;
    }

}