package com.neo.http.sample.hello.api.bean;

import com.alibaba.fastjson.JSON;

import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 16:08
 */
public class Courses {

    private Set<Course> courses;

    public Courses(Set<Course> courses) {
        this.courses = courses;
    }

    public static Courses fromJSon(String json) {
        return JSON.parseObject(json, Courses.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public Set<Course> getCourses() {
        return courses;
    }
}
