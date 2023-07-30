package com.example.retrofit_loginapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstanceClass {
    public static APLI_Interface CallApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://hardyecommerce.000webhostapp.com/Register_API/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APLI_Interface service = retrofit.create(APLI_Interface.class);
        return  service;
    }
}
