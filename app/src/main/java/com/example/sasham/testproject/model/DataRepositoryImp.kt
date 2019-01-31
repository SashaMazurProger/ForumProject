package com.example.sasham.testproject.model

import com.example.sasham.testproject.model.network.SectionsWraper
import com.example.sasham.testproject.model.network.WebestApi
import com.example.sasham.testproject.util.Converter
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class DataRepositoryImp @Inject
constructor(private val webestApi: WebestApi) : DataRepository {
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
        return webestApi
                .users()
                .map {
                    it.userWrappers!!.map {
                        User(
                                it.id,
                                it.login,
                                it.userName,
                                it.birthday,
                                it.country,
                                it.country2,
                                it.city,
                                it.sex,
                                it.state,
                                it.email,
                                it.homepage,
                                it.icq,
                                it.about,
                                it.reputation,
                                it.regDate,
                                it.msgDate,
                                it.msgCount,
                                it.lastMsgTime,
                                it.lastIp,
                                it.avatar,
                                it.created
                        )
                    }
                }

    }
}