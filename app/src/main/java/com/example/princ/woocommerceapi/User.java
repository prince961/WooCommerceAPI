package com.example.princ.woocommerceapi;

import java.net.URL;

/**
 * Created by princ on 16-01-2017.
 * user = customer
 */

public class User {
    String name, username, password;
    int age,id;

    public User(String name,int age, String username , String password  ){
        this.name = name;
        this.username = username;
        this.password = password;
        this.age = age;
    }

    public User(String username, String password) {
        this("", -1, username, password);

    }

    public User (URL url){

    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }
}
