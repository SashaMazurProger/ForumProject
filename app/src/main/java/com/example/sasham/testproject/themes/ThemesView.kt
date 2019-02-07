package com.example.sasham.testproject.themes

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.example.sasham.testproject.base.IView
import com.example.sasham.testproject.model.FavoriteTheme
import com.example.sasham.testproject.model.Section
import com.example.sasham.testproject.model.Theme

@StateStrategyType(AddToEndStrategy::class)
interface ThemesView : IView {

    fun showThemes(themeList: List<Theme>)
    fun showSections(sections: List<Section>)
    fun showFavoriteThemes(themes: List<FavoriteTheme?>)
    fun openTheme(theme: Theme)
    fun updateTheme(theme: Theme)
}
