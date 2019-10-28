package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.HttpManager;
import com.neo.http.sample.hello.api.HelloService;
import com.neo.http.sample.hello.api.bean.Course;
import com.neo.http.sample.hello.api.bean.Courses;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 15:53
 */
public class HelloServiceImpl implements HelloService {

    private HttpManager httpManager;

    public HelloServiceImpl(HttpManager httpManager) {
        this.httpManager = httpManager;
    }

    @Override
    public Courses getCourses(String name) {
        String url = httpManager.getEndPoint() + "/courses?name=" + name;
        String res = httpManager.get(url);
        return Courses.fromJson(res);
    }

    @Override
    public void createCourses(Courses courses) {
        String url = httpManager.getEndPoint() + "/courses";
        httpManager.post(url, courses.toJson());
    }

    @Override
    public void updateCourse(Course course) {
        String url = httpManager.getEndPoint() + "/course";
        httpManager.put(url, course.toJson());
    }

    @Override
    public void deleteCourse(int id) {
        String url = httpManager.getEndPoint() + "/course?id=" + id;
        httpManager.delete(url);
    }

}
