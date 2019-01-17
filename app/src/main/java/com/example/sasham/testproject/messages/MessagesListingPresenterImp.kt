package com.example.sasham.testproject.messages

import com.example.sasham.testproject.model.Message

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class MessagesListingPresenterImp @Inject
constructor(private val interactor: MessagesListingInteractor) : MessagesListingPresenter {
    private val compositeDisposable = CompositeDisposable()
    private var view: MessagesListingView? = null

    private val isViewAttached: Boolean
        get() = view != null

    override fun setView(view: MessagesListingView) {

        this.view = view
    }

    override fun fetchMessages(themeId: String) {

        view!!.onLoading()

        val disposable = interactor.fetchMessages(themeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ messages -> displayMessages(messages) },
                        { throwable ->
                            if (isViewAttached) {
                                view!!.onError(throwable.message!!)
                            }
                        })

        compositeDisposable.add(disposable)
    }

    private fun displayMessages(messages: List<Message>) {
        if (isViewAttached) {
            view!!.showThemeMessages(messages)
        }
    }

    override fun destroy() {
        view = null
        compositeDisposable.clear()
    }

    override fun loadNewData(themeId: String) {
        fetchMessages(themeId)
    }
}
