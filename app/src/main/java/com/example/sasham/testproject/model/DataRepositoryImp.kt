package com.example.sasham.testproject.model

import com.example.sasham.testproject.network.WebestApi
import com.example.sasham.testproject.users.User
import com.example.sasham.testproject.util.Converter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class DataRepositoryImp @Inject
constructor(private val webestApi: WebestApi) : DataRepository {

    override fun themes(page: String): Observable<List<Theme>> {
        return webestApi.themes(page)
                .map { themesWraper -> themesWraper.themeAnswers }
                .flatMap { themeAnswers ->
                    val themes = ArrayList<Theme>()
                    for (themeAnswer in themeAnswers!!.iterator()) {
                        themes.add(Converter.themeAnswerToTheme(themeAnswer!!)!!)
                    }
                    Observable.just(themes)
                }
    }

    override fun themeMessages(themeId: String): Observable<List<Message>> {
        return webestApi
                .themeMessages(themeId)
                .map { messageWraper -> messageWraper.messageAnswers }
                .flatMap { messageAnswers ->
                    val messages = ArrayList<Message>()
                    for (themeAnswer in messageAnswers!!.iterator()) {
                        messages.add(Converter.messageAnswerToMessage(themeAnswer!!)!!)
                    }
                    Observable.just(messages)
                }
    }

    override fun users(): Observable<List<User>> {
        return webestApi
                .users()
                .map { it.userWrappers!!.map { User(it.userName!!) } }

    }
}
