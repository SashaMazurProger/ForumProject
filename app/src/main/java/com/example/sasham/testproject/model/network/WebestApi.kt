package com.example.sasham.testproject.model.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WebestApi {

    @GET("v1/forum/topics/page/{page}/")
    fun themes(@Path("page") page: String): Observable<ThemesWraper>

    @GET("v1/forum/section/{section}/topics/page/{page}/")
    fun themesBySection(@Path("page") page: String, @Path("section") section: Int?): Observable<ThemesWraper>

    @GET("v1/forum/topic/{id}/")
    fun themeMessages(@Path("id") themeId: String): Observable<MessagesWraper>

    @GET("v1/forum/users/")
    fun users(): Observable<UsersWrapper>

    @GET("v1/forum/sections/")
    fun sections(): Observable<SectionsWraper>

    @GET("v1/forum/groups/")
    fun groups(): Observable<GroupsWraper>
}
