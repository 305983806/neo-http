package com.neo.http.sample.hello.api;

import com.neo.http.sample.hello.bean.Student;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-11 18:15
 */
public interface StudentService {



    Student getStudent(String name);

}
