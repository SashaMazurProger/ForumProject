package com.example.sasham.testproject.network;

import android.content.Context;

import com.example.sasham.testproject.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiBuilder {

    public static WebestApi createWebestApi(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.WEBEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(WebestApi.class);
    }
}
