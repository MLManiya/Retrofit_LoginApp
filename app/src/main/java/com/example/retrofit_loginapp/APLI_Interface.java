package com.example.retrofit_loginapp;

import android.text.Editable;

import com.example.retrofit_loginapp.Modal.AddProduct;
import com.example.retrofit_loginapp.Modal.LoginData;
import com.example.retrofit_loginapp.Modal.RegisterClass;
import com.example.retrofit_loginapp.Modal.UpdateProduct;
import com.example.retrofit_loginapp.Modal.Viewproduct;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface APLI_Interface {
    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterClass> registerUser(@Field("Name") String name, @Field("Email") String email, @Field("Password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginData> loginuser(@Field("Email") String email, @Field("Password") String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<AddProduct> addproduct(@Field("userid") int sellerid, @Field("pname") String name, @Field("pprize") Editable price, @Field("pdes") String description,@Field("productimage") String imagedata);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<Viewproduct> viewproduct(@Field("userid") int sellerid);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<UpdateProduct> updateproduct(@Field("id") int sellerid, @Field("name") String name, @Field("price") String price, @Field("description") String category,@Field("imagedata") String imagedata);

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<Viewproduct> deleteproduct(@Field("id") int id);

    @FormUrlEncoded
    @POST("allproduct.php")
    Call<Viewproduct> allproduct(@Field("prodata") String prodata);

}
