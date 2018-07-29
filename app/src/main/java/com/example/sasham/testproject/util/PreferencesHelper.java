package com.example.sasham.testproject.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;

public class PreferencesHelper {

    public static void setTheme(Context context, int theme) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        prefs.edit()
                .putInt(Constants.THEME, theme)
                .commit();
    }
    public static int getTheme(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        return prefs.getInt(Constants.THEME, -1);
    }
}
