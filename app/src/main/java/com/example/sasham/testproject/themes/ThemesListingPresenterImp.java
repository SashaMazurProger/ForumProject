package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThemesListingPresenterImp implements ThemesListingPresenter {
    private ThemesListingView view;
    private ThemesListingInteractor themesListingInteractor=new ThemesListingInteractorImp();
    private List<Theme> loadedThemes=new ArrayList<>();
    private int currentPage;


    @Override
    public void setView(ThemesListingView view) {
        this.view = view;
    }

    @Override
    public void firstPage() {
        currentPage=1;
        loadedThemes.clear();
        loadThemes();
    }

    @Override
    public void nextPage() {
        currentPage++;
        loadThemes();
    }


    private void loadThemes() {
        view.onLoading();
        loadedThemes.addAll(themesListingInteractor.fetchThemes(currentPage));
        view.showThemes(loadedThemes);
    }

}
