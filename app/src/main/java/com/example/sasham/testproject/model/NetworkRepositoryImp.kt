package com.example.sasham.testproject.model

import com.example.sasham.testproject.network.MessageAnswer
import com.example.sasham.testproject.network.MessageWraper
import com.example.sasham.testproject.network.ThemeAnswer
import com.example.sasham.testproject.network.ThemesWraper
import com.example.sasham.testproject.network.WebestApi
import com.example.sasham.testproject.util.Converter

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

class NetworkRepositoryImp @Inject
constructor(private val webestApi: WebestApi) : NetworkRepository {

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
}
