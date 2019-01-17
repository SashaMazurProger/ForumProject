package com.example.sasham.testproject.model

interface FavoriteThemeInfoRepository {

    val lastViewedTheme: FavoriteThemeInfo?

    fun getMostViewed(count: Int): List<FavoriteThemeInfo?>

    fun addLastViewedTheme(theme: Theme?)
}
