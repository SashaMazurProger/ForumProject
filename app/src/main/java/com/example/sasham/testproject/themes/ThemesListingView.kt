package com.example.sasham.testproject.themes

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.sasham.testproject.model.Theme

@StateStrategyType(AddToEndStrategy::class)
interface ThemesListingView : MvpView {

    fun showThemes(themeList: List<Theme>)
    fun onLoading()
    fun message(errorMessage: String?)
    fun onThemeClicked(theme: Theme)
}
