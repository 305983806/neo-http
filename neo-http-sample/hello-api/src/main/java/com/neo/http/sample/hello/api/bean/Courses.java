package com.neo.http.sample.hello.api.bean;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 16:08
 */
public class Courses implements Serializable {

    private List<Course> courses;

    public Courses() {}

    public Courses(List<Course> courses) {
        this.courses = courses;
    }

    public static Courses fromJson(String json) {
        return JSON.parseObject(json, Courses.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}