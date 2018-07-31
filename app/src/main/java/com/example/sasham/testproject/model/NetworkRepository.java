package com.example.sasham.testproject.model;

import java.util.List;

import io.reactivex.Observable;

public interface NetworkRepository {

    Observable<List<Theme>> themes( String page);
    Observable<List<Message>> themeMessages(String themeId);

}
