package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.httpservice.JoddHttpService;
import com.neo.http.sample.hello.api.HelloService;
import com.neo.http.sample.hello.api.StudentService;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 18:23
 */
public class HelloServiceImpl extends JoddHttpService implements HelloService {

    private StudentService studentService = new StudentServiceImpl(this);

    @Override
    public StudentService getStudentService() {
        return this.studentService;
    }

}
