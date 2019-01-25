package com.example.sasham.testproject.users

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BasePresenter
import com.example.sasham.testproject.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class UsersPresenter : BasePresenter<UsersView>() {

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        compositeDisposable.add(
                data.users()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    viewState.showUsers(it.map { UserItem(User(it.name!!)) })
                                }
                                ,
                                {
                                    viewState.message(it.message!!)
                                }
                        )
        )

    }
}
