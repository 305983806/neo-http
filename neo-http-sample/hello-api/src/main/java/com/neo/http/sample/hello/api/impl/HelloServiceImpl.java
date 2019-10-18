package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.httpservice.HttpService;
import com.neo.http.sample.hello.api.HelloService;
import com.neo.http.sample.hello.api.bean.Courses;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 15:53
 */
public class HelloServiceImpl implements HelloService {

    private HttpService httpService;

    public HelloServiceImpl(HttpService httpService) {
        this.httpService = httpService;
    }

    @Override
    public Courses getCourse(String name) {
        String url = httpService.getEndpoint() + "/course?name=" + name;
        String res = httpService.get(url);
        return Courses.fromJSon(res);
    }
}
