package com.example.sasham.testproject.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenter<T : MvpView> : MvpPresenter<T>(), IPresenter {

    val compositeDisposable: CompositeDisposable

    @Inject
    lateinit var data: DataRepository

    init {
        this.compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
