package com.example.sasham.testproject.model

import io.reactivex.Observable

interface NetworkRepository {

    fun themes(page: String): Observable<List<Theme>>
    fun themeMessages(themeId: String): Observable<List<Message>>

}
