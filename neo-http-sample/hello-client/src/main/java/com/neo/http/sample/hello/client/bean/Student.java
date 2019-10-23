package com.neo.http.sample.hello.client.bean;

import com.neo.http.sample.hello.api.bean.Course;

import java.util.List;
import java.util.Set;

/**
 * @Author: cp.Chen
 * @since:
 * @date: 2019-10-18 14:15
 */
public class Student {

    private int number;

    private String name;

    private String gender;

    private int age;

    private List<Course> courses;

    public Student() {}

    public Student(int number, String name, String gender, int age) {
        this.number = number;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
