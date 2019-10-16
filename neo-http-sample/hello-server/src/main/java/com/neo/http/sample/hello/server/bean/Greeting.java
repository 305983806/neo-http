package com.neo.http.sample.hello.server.bean;

public class Greeting {

    private long id;
    private String comment;

    public Greeting() {}

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
