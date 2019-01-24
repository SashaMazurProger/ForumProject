package com.example.sasham.testproject.model

import com.example.sasham.testproject.users.User
import io.reactivex.Observable

interface DataRepository {

    fun themes(page: String): Observable<List<Theme>>
    fun themeMessages(themeId: String): Observable<List<Message>>

    fun users(): Observable<List<User>>
}
