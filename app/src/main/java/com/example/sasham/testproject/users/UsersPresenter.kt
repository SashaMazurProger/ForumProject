package com.example.sasham.testproject.users

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class UsersPresenter : MvpPresenter<UsersView>() {

    @Inject
    lateinit var data: DataRepository

    private var disp: Disposable? = null

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()


        disp = data.users()
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

    }

    override fun onDestroy() {
        super.onDestroy()
        disp?.dispose()
    }
}
