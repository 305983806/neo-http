package com.neo.http.sample.hello.client.controller;

import com.neo.http.sample.hello.api.bean.Courses;
import com.neo.http.sample.hello.api.impl.HelloFactory;
import com.neo.http.sample.hello.client.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 12:10
 */
@RestController("/hello")
public class HelloController {

    @Autowired
    private HelloFactory factory;

    private Set<Student> students = new HashSet<>();

    public HelloController() {
        students.add(new Student(1, "张三", "男", 16));
        students.add(new Student(2, "李四", "男", 14));
        students.add(new Student(3, "王五", "男", 15));
        students.add(new Student(4, "赵六", "男", 18));
        students.add(new Student(5, "陈七", "男", 20));
    }

    @GetMapping("/student/{name}")
    public Student getStudent(@PathVariable("name") String name) {
        Student student = new Student();

        Iterator<Student> i = students.iterator();
        while (i.hasNext()) {
            Student s = i.next();
            if (name.equals(i.next().getName())) {
                student = s;
                break;
            }
        }

        // 获得课程列表
        Courses courses = factory.getHelloService().getCourse(name);
        student.setCourses(courses.getCourses());
        return student;
    }

}
