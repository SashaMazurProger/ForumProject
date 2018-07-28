package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.Arrays;
import java.util.List;

public class ThemesListingInteractorImp implements ThemesListingInteractor {
    @Override
    public List<Theme> fetchThemes(int page) {
        return Arrays.asList(new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null),

                new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null),

                new Theme("324", "34", "Sasha", "Topic",
                        "Message", "343234", null, null, null, null));
    }
}
