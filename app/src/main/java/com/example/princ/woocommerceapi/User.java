package com.example.princ.woocommerceapi;

import java.net.URL;

/**
 * Created by princ on 16-01-2017.
 * user = customer
 */

public class User {
    String name, username, password;
    int id;

    public User(String name,int age, String username , String password  ){
        this.name = name;
        this.username = username;
        this.password = password;

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


}
