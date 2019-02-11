package com.example.sasham.testproject.model.network

import com.example.sasham.testproject.model.User
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface WebestApi {

    @GET("v1/forum/topic/page/{page}/")
    fun themes(@Path("page") page: String): Observable<ThemesWraper>

    @GET("v1/forum/theme/{theme}/topic/page/{page}/")
    fun themesBySection(@Path("page") page: String, @Path("theme") section: Int?): Observable<ThemesWraper>

    @GET("v1/forum/topic/{id}/")
    fun themeMessages(@Path("id") themeId: String): Observable<MessagesWraper>

    @GET("v1/user/list/")
    fun users(): Observable<UsersWrapper>

    @GET("v1/forum/section/list/")
    fun sections(): Observable<SectionsWraper>

    @GET("v1/forum/group/list/")
    fun groups(): Observable<GroupsWraper>

    //    @GET("v1/user/login/")
//    fun login( email: String, pass: String): Observable<User>
//
    @POST("v1/user/login/")
    fun login(@Body body: RequestBody): Observable<ResponseBody>
}
