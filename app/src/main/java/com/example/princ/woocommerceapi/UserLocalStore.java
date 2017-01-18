package com.example.princ.woocommerceapi;

/**
 * Created by princ on 18-01-2017.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserLocalStore {

    public static String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){

        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);

    }

    public void storeUserData(User user){

        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putString("name",user.name);
        spEditor.putString("username",user.username);
        spEditor.putString("password",user.password);
        spEditor.putInt("name",user.age);
        spEditor.apply();
        //spEditor.commit();

    }

    public User GetLoggedinUser() {
        if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
            return null;
        }

        String name = userLocalDatabase.getString("name", "");
        String username = userLocalDatabase.getString("username", "");
        String password = userLocalDatabase.getString("password", "");
        int age = userLocalDatabase.getInt("age", -1);

        User user = new User(name, age, username, password);
        return user;
    }


    public void SetUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("LoggedIn", loggedIn);
        spEditor.commit();
        Log.i("BooleanUserLocalStore", Boolean.toString(userLocalDatabase.getBoolean("LoggedIn",true)));
    }

    public boolean getUserLoggedIn(){
        if (userLocalDatabase.getBoolean("LoggedIn", false)==true){
            return true;
        }
        else {
            return false;
        }
    }

    public void ClearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }




}