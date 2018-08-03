package com.example.sasham.testproject.dependencies;

import com.example.sasham.testproject.messages.MessagesFragment;
import com.example.sasham.testproject.themes.ThemesListingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface ThemesModule {

    @ThemesListingFragmentScope
    @ContributesAndroidInjector(modules = ThemesListingFragmentModule.class)
    ThemesListingFragment themesListingFragmentInjector();

}
