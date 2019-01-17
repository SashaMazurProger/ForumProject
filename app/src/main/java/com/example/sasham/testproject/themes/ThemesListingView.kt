package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.Theme

interface ThemesListingView {

    fun showThemes(themeList: List<Theme>)
    fun onLoading()
    fun onError(errorMessage: String)
    fun onThemeClicked(theme: Theme)
}
