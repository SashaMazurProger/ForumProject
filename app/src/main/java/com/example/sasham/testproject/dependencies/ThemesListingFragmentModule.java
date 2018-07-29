package com.example.sasham.testproject.dependencies;

import com.example.sasham.testproject.themes.ThemesListingInteractor;
import com.example.sasham.testproject.themes.ThemesListingInteractorImp;
import com.example.sasham.testproject.themes.ThemesListingPresenter;
import com.example.sasham.testproject.themes.ThemesListingPresenterImp;

import dagger.Binds;
import dagger.Module;

@Module
public interface ThemesListingFragmentModule {

    @ThemesListingFragmentScope
    @Binds
    ThemesListingPresenter themesListingPresenter(ThemesListingPresenterImp themesListingPresenterImp);

    @ThemesListingFragmentScope
    @Binds
    ThemesListingInteractor themesListingInteractor(ThemesListingInteractorImp themesListingInteractorImp);
}
