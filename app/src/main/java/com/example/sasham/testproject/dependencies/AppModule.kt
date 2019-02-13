package com.example.sasham.testproject.dependencies


import com.example.sasham.testproject.messages.MessagesActivity
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.DataRepositoryImp
import com.example.sasham.testproject.model.FavoriteThemeInfoRepository
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp
import com.example.sasham.testproject.themes.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton


@Module(includes = arrayOf(AndroidInjectionModule::class, NavigationModule::class))
interface AppModule {

    @ContributesAndroidInjector(modules = arrayOf(ThemesModule::class))
    fun themesInjector(): MainActivity

    @ContributesAndroidInjector(modules = arrayOf(MessagesModule::class))
    fun messagesInjector(): MessagesActivity

    @Singleton
    @Binds
    fun dataRepository(dataRepositoryImp: DataRepositoryImp): DataRepository

    @Singleton
    @Binds
    fun favoriteThemeInfoRepository(favoriteThemeInfoRepositoryImp: FavoriteThemeInfoRepositoryImp): FavoriteThemeInfoRepository
}
