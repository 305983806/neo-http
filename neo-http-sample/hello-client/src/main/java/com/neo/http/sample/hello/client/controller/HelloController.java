package com.neo.http.sample.hello.client.controller;

import com.neo.http.common.bean.HttpResponse;
import com.neo.http.common.lang.NeoHttpException;
import com.neo.http.sample.hello.api.HelloHttpManager;
import com.neo.http.sample.hello.api.bean.Course;
import com.neo.http.sample.hello.api.bean.Courses;
import com.neo.http.sample.hello.client.bean.Student;
import com.neo.http.sample.hello.client.common.HelloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 12:10
 */
@RestController
public class HelloController {

    @Autowired
    private HelloHttpManager helloHttpManager;

    private Set<Student> students = new HashSet<>();

    public HelloController() {
        students.add(new Student(1, "张三", "男", 16));
        students.add(new Student(2, "李四", "男", 14));
        students.add(new Student(3, "王五", "男", 15));
        students.add(new Student(4, "赵六", "男", 18));
        students.add(new Student(5, "陈七", "男", 20));
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam String name) {
        Student student = null;

        Iterator<Student> i = students.iterator();
        while (i.hasNext()) {
            Student s = i.next();
            if (name.equals(s.getName())) {
                student = s;
                break;
            }
        }
        if (student != null) {
            Courses courses = helloHttpManager.getHelloService().getCourses(name);
            student.setCourses(courses.getCourses());
            return student;
        } else {
            throw new RuntimeException("该学生未注册。");
        }
    }

    @PostMapping("/student")
    public HttpResponse createStudent(@RequestBody Student student) {
        Courses courses = new Courses();
        courses.setCourses(student.getCourses());
        helloHttpManager.getHelloService().createCourses(courses);
        HttpResponse resp = new HttpResponse();
        resp.setCode("0");
        resp.setMessage("OK");
        return resp;
    }

    @PutMapping("/student")
    public HttpResponse updateStudent(@RequestBody Student student) {
        List<Course> courseList = student.getCourses();
        for (Course c : courseList) {
            helloHttpManager.getHelloService().updateCourse(c);
        }
        HttpResponse resp = new HttpResponse();
        resp.setCode("0");
        resp.setMessage("OK");
        return resp;
    }

    @DeleteMapping("/student/{id}")
    public HttpResponse deleteStudent(@PathVariable(name = "id") int id) {
        Student student = null;
        Iterator<Student> i = students.iterator();
        while (i.hasNext()) {
            Student s = i.next();
            if (id == s.getNumber()) {
                student = s;
                break;
            }
        }

        if (student != null) {

            Course course = new Course(1, "语文");
            List<Course> courses = new ArrayList<>();
            courses.add(course);
            student.setCourses(courses);

            for (Course c : student.getCourses()) {
                try {
                    helloHttpManager.getHelloService().deleteCourse(c.getId());
                } catch (NeoHttpException e) {
                    throw new HelloException(e.getMessage());
                }
            }
            HttpResponse resp = new HttpResponse();
            resp.setCode("0");
            resp.setMessage("OK");
            return resp;
        } else {
            throw new RuntimeException("该未有学生注册。");
        }
    }

}