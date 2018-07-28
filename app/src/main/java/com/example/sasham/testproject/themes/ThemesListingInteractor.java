package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.List;

import io.reactivex.Observable;

public interface ThemesListingInteractor {

    Observable<List<Theme>> fetchThemes(int page);
}
