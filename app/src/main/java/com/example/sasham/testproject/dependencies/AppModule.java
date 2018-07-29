package com.example.sasham.testproject.dependencies;


import com.example.sasham.testproject.themes.ThemesActivity;

import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;


@Module(includes = AndroidInjectionModule.class)
public interface AppModule {

    @ContributesAndroidInjector(modules = ThemesModule.class)
    ThemesActivity themesInjector();
}
