package com.example.sasham.testproject.model

import com.example.sasham.testproject.model.db.RoomDb
import com.example.sasham.testproject.model.db.UserTable
import com.example.sasham.testproject.model.network.SectionsWraper
import com.example.sasham.testproject.model.network.WebestApi
import com.example.sasham.testproject.util.Converter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class DataRepositoryImp @Inject
constructor(private val webestApi: WebestApi, private val db: RoomDb) : DataRepository {

    override fun users(s: String): Observable<List<User>> {
        return Observable.fromCallable { db.userDao().count() }
                .flatMap<List<User>> {
                    if (it > 0) {
                        return@flatMap Observable.fromCallable {
                            db.userDao().users(s)
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
                                    return@map db.userDao().users(s)
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
            return webestApi.themes(page)
                    .map { themesWraper -> themesWraper.themeAnswers }
                    .flatMap { themeAnswers ->
                        val themes = ArrayList<Theme>()
                        for (themeAnswer in themeAnswers!!.iterator()) {
                            themes.add(Converter.themeAnswerToTheme(themeAnswer!!)!!)
                        }
                        Observable.just(themes)
                    }
        } else {
            return webestApi.themesBySection(page, section.id)
                    .map { themesWraper -> themesWraper.themeAnswers }
                    .flatMap { themeAnswers ->
                        val themes = ArrayList<Theme>()
                        for (themeAnswer in themeAnswers!!.iterator()) {
                            themes.add(Converter.themeAnswerToTheme(themeAnswer!!)!!)
                        }
                        Observable.just(themes)
                    }
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

