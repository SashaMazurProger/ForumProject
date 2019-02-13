package com.example.sasham.testproject.themes

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.sasham.testproject.base.IView

@StateStrategyType(AddToEndStrategy::class)
interface MainView : IView {

    //    fun showAccountScreen()
//    fun showAuthScreen()
//    fun showThemesScreen()
//    fun showUsersScreen()
    @StateStrategyType(SkipStrategy::class)
    fun selectTab(tab: String)
}
