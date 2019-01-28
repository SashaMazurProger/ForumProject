package com.example.sasham.testproject.users

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BasePresenter
import com.example.sasham.testproject.model.User

@InjectViewState
class UserDetPresenter(val user: User) : BasePresenter<UserDetailsView>() {

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.showUser(user)
    }
}