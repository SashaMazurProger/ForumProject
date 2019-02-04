package com.example.sasham.testproject.model

import io.reactivex.Observable

interface DataRepository {

    fun themes(page: String, section: Section?): Observable<List<Theme>>
    fun themeMessages(themeId: String): Observable<List<Message>>
    fun users(): Observable<List<User>>
    fun sections(): Observable<List<Section>>
    fun users(s: String): Observable<List<User>>
}
