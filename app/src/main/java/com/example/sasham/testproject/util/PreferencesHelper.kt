package com.example.sasham.testproject.util

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

import com.example.sasham.testproject.Constants

object PreferencesHelper {

    fun setTheme(context: Context, theme: Int) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit()
                .putInt(Constants.APP_THEME, theme)
                .commit()
    }

    fun getTheme(context: Context): Int {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getInt(Constants.APP_THEME, -1)
    }

    fun setString(context: Context, key: String, value: String) {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.edit()
                .putString(key, value)
                .commit()
    }

    fun getString(context: Context, key: String, defValue: String): String? {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(key, defValue)
    }
}
