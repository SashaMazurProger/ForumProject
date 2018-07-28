package com.example.sasham.testproject.network;

import com.example.sasham.testproject.model.Theme;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebestApi {

    @GET("forum/fresh/{page}/")
    Observable<ThemesWraper> themes(@Path("page") String page);
}
