package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThemesListingPresenterImp implements ThemesListingPresenter {
    private ThemesListingView view;
    private ThemesListingInteractor interactor=new ThemesListingInteractorImp();

    @Override
    public void setView(ThemesListingView view) {
        this.view = view;
    }

    @Override
    public void firstPage() {

        view.onLoading();

        view.showThemes(interactor.fetchThemes(1));
    }
}
