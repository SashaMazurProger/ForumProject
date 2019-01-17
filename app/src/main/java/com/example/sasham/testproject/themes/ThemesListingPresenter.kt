package com.example.sasham.testproject.themes

interface ThemesListingPresenter {

    fun setView(view: ThemesListingView)
    fun firstPage()
    fun nextPage()
    fun destroy()
    fun loadNewData()
}
