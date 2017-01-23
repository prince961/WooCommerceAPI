package com.example.princ.woocommerceapi;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mohit on 10/5/2016.
 */

public class Controller extends Application {

    private ArrayList<ModelProducts> myProducts = new ArrayList<ModelProducts>();
    private ArrayList<ModelProducts> noodleProducts = new ArrayList<ModelProducts>();
    private ArrayList<ModelProducts> AppetizerProductAl = new ArrayList<ModelProducts>();
    private ArrayList<ModelProducts> Breads = new ArrayList<ModelProducts>();
    private ArrayList<ModelProducts> VegMainCourse = new ArrayList<ModelProducts>();
    private ArrayList<ModelProducts> NonVegMainAl = new ArrayList<ModelProducts>();

    private  ModelCart myCart = new ModelCart();


    public ModelProducts getNoodleP(int pPosition) {

        return noodleProducts.get(pPosition);
    }

    public void addAppetizertoAl(List<ModelProducts> productList){
        AppetizerProductAl.addAll(productList);}

    public ArrayList<ModelProducts> getAppetizerProductAl(){
        return AppetizerProductAl;
    }

    public void addNoodlePs(List<ModelProducts> productList){
        noodleProducts.addAll(productList);
    }

    public void setProducts(ModelProducts Products) {

        myProducts.add(Products);

    }

    public ModelCart getCart() {

        return myCart;

    }

    public int getProductsArraylistSize() {

        return myProducts.size();
    }

    public boolean checkid(int id) {

        boolean isTrue = false;
        for (int j = 0; j < noodleProducts.size(); j++) {
            if (noodleProducts.get(j).getId() == id) {
                isTrue =true;
                break;
            }else {isTrue = false;}
        }

        return isTrue;
    }

    public ArrayList<ModelProducts> getNoodleProducts() {
        return noodleProducts;
    }

    public boolean checkAppetizerId(int id) {

        boolean isTrue = false;
        for (int j = 0; j < AppetizerProductAl.size(); j++) {
            if (AppetizerProductAl.get(j).getId() == id) {
                isTrue =true;
                break;
            }else {isTrue = false;}
        }

        return isTrue;
    }

    public ArrayList<ModelProducts> getNonVegMainAl() {
        return NonVegMainAl;
    }

    public void setNonVegMainAl(ArrayList<ModelProducts> nonVegMainAl) {
        NonVegMainAl.addAll(nonVegMainAl);
    }

    public boolean checkNonVegMainCourseId(int id) {

        boolean isTrue = false;
        for (int j = 0; j < NonVegMainAl.size(); j++) {
            if (NonVegMainAl.get(j).getId() == id) {
                isTrue =true;
                break;
            }else {isTrue = false;}
        }

        return isTrue;
    }

    public ArrayList<ModelProducts> getBreads() {
        return Breads;
    }

    public void setBreads(ArrayList<ModelProducts> breads) {
        Breads.addAll(breads);
    }

    public ArrayList<ModelProducts> getVegMainCourse() {
        return VegMainCourse;
    }

    public void setVegMainCourse(ArrayList<ModelProducts> vegMainCourse) {
        VegMainCourse.addAll(vegMainCourse);
    }

    public boolean checkVegMainCourseId(int id) {

        boolean isTrue = false;
        for (int j = 0; j < VegMainCourse.size(); j++) {
            if (VegMainCourse.get(j).getId() == id) {
                isTrue =true;
                break;
            }else {isTrue = false;}
        }

        return isTrue;
    }

    public boolean checkbreadsId(int id) {

        boolean isTrue = false;
        for (int j = 0; j < Breads.size(); j++) {
            if (Breads.get(j).getId() == id) {
                isTrue =true;
                break;
            }else {isTrue = false;}
        }

        return isTrue;
    }
}