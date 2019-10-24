package com.neo.http.sample.hello.api.impl;

import com.neo.http.client.httpservice.HttpService;
import com.neo.http.sample.hello.api.HelloService;
import com.neo.http.sample.hello.api.bean.Course;
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
    public Courses getCourses(String name) {
        String url = httpService.getEndpoint() + "/courses?name=" + name;
        String res = httpService.get(url);
        return Courses.fromJson(res);
    }

    @Override
    public void createCourses(Courses courses) {
        String url = httpService.getEndpoint() + "/courses";
        httpService.post(url, courses.toJson());
    }

    @Override
    public void updateCourse(Course course) {
        String url = httpService.getEndpoint() + "/course";
        httpService.put(url, course.toJson());
    }

    @Override
    public void deleteCourse(int id) {
        String url = httpService.getEndpoint() + "/course?id=" + id;
        httpService.delete(url);
    }

}
