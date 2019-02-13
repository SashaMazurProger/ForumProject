package com.example.sasham.testproject.account

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BasePresenter
import com.example.sasham.testproject.navigation.Screens
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router

@InjectViewState
class AuthPresenter(val localRouter: Router) : BasePresenter<AuthView>() {

    init {
        App.instance!!.appComp!!.inject(this)
    }


    fun onLoginClicked(email: String, pass: String) {
        compositeDisposable.add(
                data.login(email, pass)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            localRouter.replaceScreen(Screens.AccountScreen)
                        }, {
                            if (!it.message.isNullOrEmpty()) viewState.message(it.message!!)
                        })
        )
    }
}