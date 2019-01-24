package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.themes.ThemesListingInteractor
import com.example.sasham.testproject.themes.ThemesListingInteractorImp
import com.example.sasham.testproject.themes.ThemesListingPresenter
import com.example.sasham.testproject.themes.ThemesListingPresenterImp

import dagger.Binds
import dagger.Module

@Module
interface ThemesListingFragmentModule {


    @ThemesListingFragmentScope
    @Binds
    fun themesListingPresenter(themesListingPresenterImp: ThemesListingPresenterImp): ThemesListingPresenter

    @ThemesListingFragmentScope
    @Binds
    fun themesListingInteractor(themesListingInteractorImp: ThemesListingInteractorImp): ThemesListingInteractor
}
