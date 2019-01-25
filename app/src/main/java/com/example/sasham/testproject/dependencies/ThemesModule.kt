package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.themes.ThemesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ThemesModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(ThemesListingFragmentModule::class))
    fun themesFragment(): ThemesFragment
}

@Module
interface ThemesListingFragmentModule {

}
