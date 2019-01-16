package com.example.sasham.testproject.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class FavoriteThemeInfoRepositoryImp implements FavoriteThemeInfoRepository {

    private static final String LAST_VIEWED_THEME_ID = "LAST_VIEWED_THEME_ID";
    private static final String LAST_VIEWED_THEME_TIME = "LAST_VIEWED_THEME_TIME";
    private static final String LAST_VIEWED_THEME_TITLE = "LAST_VIEWED_THEME_TITLE";
    private final SharedPreferences prefs;
    private List<FavoriteThemeInfo> favoritesThemes = new ArrayList<>();
    private Context context;

    @Inject
    public FavoriteThemeInfoRepositoryImp(Context context) {
        this.context = context;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public List<FavoriteThemeInfo> getMostViewed(int count) {
        return favoritesThemes.subList(0, count);
    }

    @Override
    public FavoriteThemeInfo getLastViewedTheme() {

        String id = prefs.getString(LAST_VIEWED_THEME_ID, null);

        if (id == null) {
            return null;
        }

        return new FavoriteThemeInfo(
                id,
                0,
                prefs.getLong(LAST_VIEWED_THEME_TIME, 0),
                prefs.getString(LAST_VIEWED_THEME_TITLE, null)
        );
    }

    @Override
    public void addLastViewedTheme(Theme theme) {
        prefs.edit().putString(LAST_VIEWED_THEME_ID, theme.getId()).commit();
        prefs.edit().putLong(LAST_VIEWED_THEME_TIME, Calendar.getInstance().getTimeInMillis()).commit();
        prefs.edit().putString(LAST_VIEWED_THEME_TITLE, theme.getForumName()).commit();
    }
}
