package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.List;

public interface ThemesListingView {

    void showThemes(List<Theme> themeList);
    void onLoading();
    void onError(String errorMessage);
}
