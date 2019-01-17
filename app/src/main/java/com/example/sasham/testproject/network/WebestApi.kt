package com.example.sasham.testproject.network

import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.Theme

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebestApi {

    @GET("forum/fresh/{page}/")
    fun themes(@Path("page") page: String): Observable<ThemesWraper>

    @GET("forum/topic/{id}/")
    fun themeMessages(@Path("id") themeId: String): Observable<MessageWraper>
}
