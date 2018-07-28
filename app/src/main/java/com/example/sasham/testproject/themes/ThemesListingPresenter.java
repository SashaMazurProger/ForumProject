package com.example.sasham.testproject.themes;

public interface ThemesListingPresenter {

    void setView(ThemesListingView view);
    void firstPage();
    void nextPage();
    void destroy();
}
