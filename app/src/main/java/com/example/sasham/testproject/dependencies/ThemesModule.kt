package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.messages.MessagesFragment
import com.example.sasham.testproject.themes.ThemesListingFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ThemesModule {

    @ThemesListingFragmentScope
    @ContributesAndroidInjector(modules = arrayOf(ThemesListingFragmentModule::class))
    fun themesListingFragmentInjector(): ThemesListingFragment

}
