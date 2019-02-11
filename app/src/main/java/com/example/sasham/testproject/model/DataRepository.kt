package com.example.sasham.testproject.model

import hu.akarnokd.rxjava2.subjects.DispatchWorkSubject
import io.reactivex.Completable
import io.reactivex.Observable

interface DataRepository {

    fun themes(page: String, section: Section?): Observable<List<Theme>>
    fun themeMessages(themeId: String): Observable<List<Message>>
    fun users(): Observable<List<User>>
    fun sections(): Observable<List<Section>>
    fun users(s: String): Observable<List<User>>
    fun favoriteThemes(): Observable<List<FavoriteTheme>>
    fun addFavoriteTheme(theme: FavoriteTheme): Completable
    fun removeFavoriteTheme(theme: FavoriteTheme): Completable
    fun updateFavoriteThemeViewTime(theme: Theme)
    fun login(email: String, pass: String): Observable<User>

    val favoriteStatusChangeEvent: DispatchWorkSubject<Theme>
}
