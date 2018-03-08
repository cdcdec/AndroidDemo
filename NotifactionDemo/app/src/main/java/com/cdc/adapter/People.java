package com.cdc.adapter;

/**
 * Created by cdc on 2018/3/8.
 */

public class People {
    private String name;
    private int age;
    private String position;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(name);
        sb.append(age+"");
        sb.append(position);
        return sb.toString();
    }
}
