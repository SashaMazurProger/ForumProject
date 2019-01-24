package com.example.sasham.testproject.model

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import java.util.ArrayList
import java.util.Calendar

import javax.inject.Inject

class FavoriteThemeInfoRepositoryImp @Inject
constructor(private val context: Context) : FavoriteThemeInfoRepository {
    private val prefs: SharedPreferences
    private val favoritesThemes = ArrayList<FavoriteThemeInfo>()

    override val lastViewedTheme: FavoriteThemeInfo?
        get() {

            val id = prefs.getString(LAST_VIEWED_THEME_ID, null) ?: return null

            return FavoriteThemeInfo(
                    id,
                    0,
                    prefs.getLong(LAST_VIEWED_THEME_TIME, 0),
                    prefs.getString(LAST_VIEWED_THEME_TITLE, null)
            )
        }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
    }

    override fun getMostViewed(count: Int): List<FavoriteThemeInfo> {
        return favoritesThemes.subList(0, count)
    }

    override fun addLastViewedTheme(theme: Theme?) {
        prefs.edit().putString(LAST_VIEWED_THEME_ID, theme!!.id).commit()
        prefs.edit().putLong(LAST_VIEWED_THEME_TIME, Calendar.getInstance().timeInMillis).commit()
        prefs.edit().putString(LAST_VIEWED_THEME_TITLE, theme.topicText).commit()
    }

    companion object {

        private val LAST_VIEWED_THEME_ID = "LAST_VIEWED_THEME_ID"
        private val LAST_VIEWED_THEME_TIME = "LAST_VIEWED_THEME_TIME"
        private val LAST_VIEWED_THEME_TITLE = "LAST_VIEWED_THEME_TITLE"
    }
}
