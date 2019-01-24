package com.example.sasham.testproject.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T : IView> : IPresenter<T> {

    val compositeDisposable: CompositeDisposable
    var mvpView: T? = null
        private set

    val isViewAttached: Boolean
        get() = mvpView != null

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onAttach(view: T) {
        this.mvpView = view
    }

    override fun onDetach() {
        this.mvpView = null
        compositeDisposable.clear()
    }
}
