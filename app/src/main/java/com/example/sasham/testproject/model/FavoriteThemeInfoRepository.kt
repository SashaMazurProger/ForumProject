package com.example.sasham.testproject.model

interface FavoriteThemeInfoRepository {
    fun getMostViewed(count: Int): List<FavoriteThemeInfo?>
    fun addLastViewedTheme(theme: Theme?)
}
