package com.example.sasham.testproject.network

import com.example.sasham.testproject.model.UsersWrapper
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface WebestApi {

    @GET("v1/forum/topics/page/{page}/")
    fun themes(@Path("page") page: String): Observable<ThemesWraper>

    @GET("v1/forum/topic/{id}/")
    fun themeMessages(@Path("id") themeId: String): Observable<MessageWraper>

    @GET("v1/forum/users/")
    fun users(): Observable<UsersWrapper>
}
