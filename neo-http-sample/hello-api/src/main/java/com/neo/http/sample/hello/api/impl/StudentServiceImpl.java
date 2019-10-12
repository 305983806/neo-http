package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.httpservice.HttpService;
import com.neo.http.sample.hello.api.StudentService;
import com.neo.http.sample.hello.bean.Student;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 18:24
 */
public class StudentServiceImpl implements StudentService {

    private HttpService httpService;

    private String endpoint;

    public StudentServiceImpl(HttpService httpService) {
        this.httpService = httpService;
        this.endpoint = httpService.getEndpoint();
    }

    @Override
    public void getStudent(String name) {
        String uri = "/student/get?name=" +name;
        String url = endpoint + uri;
        httpService.get(url, null);

    }
}
