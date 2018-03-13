package com.xmg.testcomponent.bean;

/**
 * Created by xmg on 2018/3/1.
 */

public  class Person {
    public String id;
    public String name;
    public int age;
    public String address;

    /**
     * 使用FastJson需要一个无参的构造方法才可以解析。
     */
    public Person() {
    }

    public Person(String id, String name, int age, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
    }

}