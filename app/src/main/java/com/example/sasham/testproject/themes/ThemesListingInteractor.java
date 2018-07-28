package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.List;

public interface ThemesListingInteractor {

    List<Theme> fetchThemes(int page);
}
