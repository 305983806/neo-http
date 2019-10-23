package com.neo.http.sample.hello.server.controller;

import com.neo.http.sample.hello.api.bean.Course;
import com.neo.http.sample.hello.api.bean.Courses;
import com.neo.http.sample.hello.server.common.HelloError;
import com.neo.http.sample.hello.server.common.HelloException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 15:14
 */
@RestController
public class HelloController {

    @GetMapping("/course")
    public Courses getCourse(@RequestParam String name) {
        if ("王五".equals(name)) {
            throw new HelloException(HelloError.HELLO_NOT_REGISTER);
        }
        List<Course> courses = new LinkedList<>();
        courses.add(new Course(1, "语文"));
        courses.add(new Course(2, "数学"));
        courses.add(new Course(3, "英语"));
        courses.add(new Course(4, "物理"));
        courses.add(new Course(5, "化学"));
        courses.add(new Course(6, "生物"));
        courses.add(new Course(7, "政治"));
        courses.add(new Course(8, "地理"));
        courses.add(new Course(9, "历史"));
        return new Courses(courses);
    }

}
