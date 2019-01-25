package com.example.sasham.testproject.messages

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BasePresenter
import com.example.sasham.testproject.model.Theme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class MessagesPresenter(private val theme: Theme) : BasePresenter<MessagesListingView>() {

    init {
        App.instance!!.appComp!!.inject(this)
    }

    fun fetchMessages() {

        val disposable = data.themeMessages(theme.id!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ messages ->
                    viewState.showThemeMessages(messages)
                },
                        { throwable -> viewState.message(throwable.message!!) })

        compositeDisposable.add(disposable)
    }
}
