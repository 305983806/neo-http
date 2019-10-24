package com.neo.http.sample.hello.server.controller;

import com.neo.http.sample.hello.api.bean.Course;
import com.neo.http.sample.hello.api.bean.Courses;
import com.neo.http.sample.hello.server.common.HelloError;
import com.neo.http.sample.hello.server.common.HelloException;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 15:14
 */
@RestController
public class HelloController {

    @GetMapping("/courses")
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

    @PostMapping("/courses")
    public void createCourse(@RequestBody Courses courses) {
        if (courses.getCourseList() == null || courses.getCourseList().size() == 0) {
            throw new HelloException(HelloError.INVALID_PARAMETER);
        }
        System.out.println("保存 courses 成功。");
    }

    @PutMapping("/course")
    public void updateCourses(@RequestBody Course course) {
        if ("语文".equals(course.getName())) {
            throw new HelloException(HelloError.INVALID_PARAMETER);
        }
        System.out.println("更新 course 成功。");
    }

    @DeleteMapping("/course")
    public void deleteCourses(@RequestParam int id) {
        if (id == 3) {
            throw new HelloException(HelloError.INVALID_PARAMETER);
        }
        System.out.println("删除 course 成功");
    }

}
