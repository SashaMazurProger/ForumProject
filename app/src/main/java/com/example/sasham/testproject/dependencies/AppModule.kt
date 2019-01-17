package com.example.sasham.testproject.dependencies


import com.example.sasham.testproject.messages.MessagesActivity
import com.example.sasham.testproject.model.FavoriteThemeInfoRepository
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp
import com.example.sasham.testproject.model.NetworkRepository
import com.example.sasham.testproject.model.NetworkRepositoryImp
import com.example.sasham.testproject.themes.ThemesActivity

import javax.inject.Singleton

import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector


@Module(includes = arrayOf(AndroidInjectionModule::class))
interface AppModule {

    @ContributesAndroidInjector(modules = arrayOf(ThemesModule::class))
    fun themesInjector(): ThemesActivity

    @ContributesAndroidInjector(modules = arrayOf(MessagesModule::class))
    fun messagesInjector(): MessagesActivity

    @Singleton
    @Binds
    fun networkRepository(networkRepositoryImp: NetworkRepositoryImp): NetworkRepository

    @Singleton
    @Binds
    fun favoriteThemeInfoRepository(favoriteThemeInfoRepositoryImp: FavoriteThemeInfoRepositoryImp): FavoriteThemeInfoRepository
}
