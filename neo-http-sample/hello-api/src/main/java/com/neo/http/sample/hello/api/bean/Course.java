package com.neo.http.sample.hello.api.bean;

import com.alibaba.fastjson.JSON;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 14:19
 */
public class Course {

    // 课程 ID
    private int id;

    // 课程名称
    private String name;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Course fromJson(String json) {
        return JSON.parseObject(json, Course.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
