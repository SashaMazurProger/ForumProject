package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.Arrays;

public class ThemesListingPresenterImp implements ThemesListingPresenter {
    private ThemesListingView view;

    @Override
    public void setView(ThemesListingView view) {
        this.view = view;
    }

    @Override
    public void firstPage() {
        view.showThemes(Arrays.asList(new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null),

                new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null),

                new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null)));
    }
}
