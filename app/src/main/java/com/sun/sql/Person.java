package com.sun.sql;

import java.io.Serializable;

/**
 * Created by sun on 2018/3/5.
 */

public class Person implements Serializable {
    String name;
    int age;
    boolean male;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

}
