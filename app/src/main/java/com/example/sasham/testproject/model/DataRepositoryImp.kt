package com.example.sasham.testproject.model

import android.content.SharedPreferences
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.model.db.FavoriteThemeTable
import com.example.sasham.testproject.model.db.RoomDb
import com.example.sasham.testproject.model.db.UserTable
import com.example.sasham.testproject.model.network.SectionsWraper
import com.example.sasham.testproject.model.network.ThemesWraper
import com.example.sasham.testproject.model.network.UserWrapper
import com.example.sasham.testproject.model.network.WebestApi
import com.example.sasham.testproject.util.Converter
import com.google.gson.Gson
import hu.akarnokd.rxjava2.subjects.DispatchWorkSubject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import java.util.*
import javax.inject.Inject

class DataRepositoryImp @Inject
constructor(private val webestApi: WebestApi, private val db: RoomDb, private val prefs: SharedPreferences) : DataRepository {


    override val favoriteStatusChangeEvent = DispatchWorkSubject.create<Theme>(Schedulers.io())


    override fun login(email: String, pass: String): Observable<User> {

        val body = RequestBody.create(MediaType.get("application/json"), "{\"login\":\"${email}\",\"password\":\"${pass}\"}")
        return webestApi.login(body)
                .map {
                    val body = it.string()
                    val userWraper = Gson().fromJson<UserWrapper>(body, UserWrapper::class.java)
//                    val passHash = Gson().toJsonTree(body).asJsonObject.get("password").asString
//                    prefs.edit().putString(Constants.PASS_HASH,passHash).commit()
                    return@map User.copy(userWraper)
                }
    }

    override fun updateFavoriteThemeViewTime(theme: Theme) {
        db.favoriteThemeDao()
                .themes()
                .find { it.themeId.toString() == theme.id }
                .let {
                    it?.lastTimeViewedInMillis = Calendar.getInstance().timeInMillis
                    if (it != null) {
                        db.favoriteThemeDao()
                                .update(it)
                    }
                }
    }

    override fun removeFavoriteTheme(theme: FavoriteTheme): Completable {
        return Completable.fromAction { db.favoriteThemeDao().delete(FavoriteThemeTable.copy(theme)) }
    }


    override fun addFavoriteTheme(theme: FavoriteTheme): Completable {
        return Completable.fromAction { db.favoriteThemeDao().saveTheme(FavoriteThemeTable.copy(theme)) }
    }


    override fun favoriteThemes(): Observable<List<FavoriteTheme>> {

        return Observable.fromCallable { db.favoriteThemeDao().themes() }
                .map { it.map { FavoriteTheme.copy(it) } }
    }

    override fun users(s: String): Observable<List<User>> {

        val sqlSearch = "%$s%"

        return Observable.fromCallable { db.userDao().count() }
                .flatMap<List<User>> {
                    if (it > 0) {
                        return@flatMap Observable.fromCallable {
                            db.userDao().users(sqlSearch)
                                    .map {
                                        User.copy(it)
                                    }
                        }
                    } else {
                        return@flatMap webestApi.users()
                                .map {
                                    it.userWrappers!!.map {
                                        User.copy(it)
                                    }
                                }
                                .map {
                                    db.userDao().saveUsers(it.map {
                                        UserTable.copy(it)
                                    })
                                    return@map db.userDao().users(sqlSearch)
                                            .map {
                                                User.copy(it)
                                            }
                                }
                    }

                }
    }

    override fun sections(): Observable<List<Section>> {
        return webestApi.sections()
                .map { t: SectionsWraper -> t.sections!!.map { Section(it.id, it.groupId, it.name, it.description) } }
    }

    override fun themes(page: String, section: Section?): Observable<List<Theme>> {

        if (section == null) {
            return Observable.zip(webestApi.themes(page), Observable.fromCallable { db.favoriteThemeDao().themes() },
                    object : BiFunction<ThemesWraper, List<FavoriteThemeTable>, List<Theme>> {
                        override fun apply(t1: ThemesWraper, favorites: List<FavoriteThemeTable>): List<Theme> {
                            val themes = t1.themeAnswers
                                    ?.map { Converter.themeAnswerToTheme(it)!! }

                            themes?.filter { answer: Theme -> favorites.any { it.themeId == answer.id?.toInt() } }
                                    ?.map {
                                        it.isFavorite = true
                                    }

                            return themes!!
                        }

                    })
//                    .map { themesWraper -> themesWraper.themeAnswers }
//                    .flatMap { themeAnswers ->
//                        val themes = ArrayList<Theme>()
//                        for (themeAnswer in themeAnswers!!.iterator()) {
//                            themes.add(Converter.themeAnswerToTheme(themeAnswer!!)!!)
//                        }
//                        Observable.just(themes)
//                    }
//                    .
        } else {
//            return webestApi.themesBySection(page, section.id)
//                    .map { themesWraper -> themesWraper.themeAnswers }
//                    .flatMap { themeAnswers ->
//                        val themes = ArrayList<Theme>()
//                        for (themeAnswer in themeAnswers!!.iterator()) {
//                            themes.add(Converter.themeAnswerToTheme(themeAnswer!!)!!)
//                        }
//                        Observable.just(themes)
//                    }
            return Observable.zip(webestApi.themesBySection(page, section.id), Observable.just(db.favoriteThemeDao().themes()),
                    object : BiFunction<ThemesWraper, List<FavoriteThemeTable>, List<Theme>> {
                        override fun apply(t1: ThemesWraper, favorites: List<FavoriteThemeTable>): List<Theme> {
                            val themes = t1.themeAnswers
                                    ?.map { Converter.themeAnswerToTheme(it!!)!! }

                            themes?.filter { answer: Theme -> favorites.any { it.themeId == answer.id?.toInt() } }
                                    ?.map {
                                        it.isFavorite = true
                                    }

                            return themes!!
                        }

                    })
        }
    }

    override fun themeMessages(themeId: String): Observable<List<Message>> {
        return webestApi
                .themeMessages(themeId)
                .map { messageWraper -> messageWraper.messageAnswers }
                .flatMap { messageAnswers ->
                    val messages = ArrayList<Message>()
                    for (themeAnswer in messageAnswers!!.iterator()) {
                        themeAnswer == null ?: messages.add(Converter.messageAnswerToMessage(themeAnswer)!!)
                    }
                    Observable.just(messages)
                }
    }

    override fun users(): Observable<List<User>> {
        return Observable.fromCallable { db.userDao().count() }
                .flatMap<List<User>> {
                    if (it > 0) {
                        return@flatMap Observable.fromCallable {
                            db.userDao().users()
                                    .map {
                                        User.copy(it)
                                    }
                        }
                    } else {
                        return@flatMap webestApi.users()
                                .map {
                                    it.userWrappers!!.map {
                                        User.copy(it)
                                    }
                                }
                                .map {
                                    db.userDao().saveUsers(it.map {
                                        UserTable.copy(it)
                                    })
                                    return@map it
                                }
                    }

                }
    }
}

