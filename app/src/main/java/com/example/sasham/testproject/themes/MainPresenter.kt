package com.example.sasham.testproject.themes

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.navigation.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class MainPresenter(var router: Router) : MvpPresenter<MainView>() {

    @Inject
    lateinit var data: DataRepository

    init {
        App.instance!!.appComp!!.inject(this)
    }

    fun showAccountScreenClicked() {
        if (data.isLogined()) {
            viewState.selectTab(Screens.AccountScreen.screenKey)
        } else {
            viewState.selectTab(Screens.AuthScreen.screenKey)
        }
    }

    fun showThemesScreenClicked() {
        viewState.selectTab(Screens.ThemesScreen.screenKey)
    }

    fun showUsersScreenClicked() {
        viewState.selectTab(Screens.UsersScreen.screenKey)

    }

    fun logout() {
        data.logout()
        showAccountScreenClicked()
    }

    fun onBackPressed() {
        router.exit()
    }
}