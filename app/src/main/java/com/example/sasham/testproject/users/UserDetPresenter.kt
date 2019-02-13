package com.example.sasham.testproject.users

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BasePresenter
import com.example.sasham.testproject.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class UserDetPresenter(val user: User? = null, val userId: Int? = null) : BasePresenter<UserDetailsView>() {

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        if (user != null) {
            viewState.showUser(user)
        } else {
            data.users()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        val user = it.find { it.id == userId }
                        if (user != null) {
                            viewState.showUser(user)
                        }

                    }, {

                    })
        }
    }
}