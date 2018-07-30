package com.example.sasham.testproject.dependencies;


import com.example.sasham.testproject.model.NetworkRepository;
import com.example.sasham.testproject.model.NetworkRepositoryImp;
import com.example.sasham.testproject.themes.ThemesActivity;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjectionModule;
import dagger.android.ContributesAndroidInjector;


@Module(includes = AndroidInjectionModule.class)
public interface AppModule {

    @ContributesAndroidInjector(modules = ThemesModule.class)
    ThemesActivity themesInjector();

    @Singleton
    @Binds
    NetworkRepository networkRepository(NetworkRepositoryImp networkRepositoryImp);
}
