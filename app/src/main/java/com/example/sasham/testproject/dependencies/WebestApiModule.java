package com.example.sasham.testproject.dependencies;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.network.WebestApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class WebestApiModule {

    @Singleton
    @Provides
    WebestApi webestApi(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Constants.WEBEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(WebestApi.class);
    }
}
