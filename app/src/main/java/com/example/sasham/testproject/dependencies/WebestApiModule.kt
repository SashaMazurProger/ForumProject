package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.model.network.WebestApi
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class WebestApiModule {

    @Singleton
    @Provides
    fun webestApi(): WebestApi {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val retrofit = Retrofit.Builder()
                .baseUrl(Constants.WEBEST_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OkHttpClient.Builder()
                        .addInterceptor(loggingInterceptor)
                        .build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        return retrofit.create(WebestApi::class.java)
    }
}
