package com.neo.http.sample.hello.api.bean;

import com.alibaba.fastjson.JSON;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 12:03
 */
public class Greeting {

    private long id;
    private String comment;

    public Greeting() {}

    public static Greeting fromJson(String json) {
        return JSON.parseObject(json, Greeting.class);
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }

    public Greeting(long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

}
