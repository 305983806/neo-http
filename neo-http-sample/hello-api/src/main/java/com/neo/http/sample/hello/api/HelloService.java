package com.neo.http.sample.hello.api;

import com.neo.http.sample.hello.api.bean.Course;
import com.neo.http.sample.hello.api.bean.Courses;

import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 18:20
 */
public interface HelloService {

    Courses getCourse(String name);

}
