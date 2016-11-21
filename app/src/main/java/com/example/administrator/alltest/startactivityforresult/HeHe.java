package com.example.administrator.alltest.startactivityforresult;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/11/18 0018.
 */

public class HeHe implements Serializable{

    private String name;
    private String age;

    public HeHe(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "HeHe{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
