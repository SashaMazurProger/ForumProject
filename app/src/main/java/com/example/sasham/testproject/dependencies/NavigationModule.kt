package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.navigation.LocalCiceroneHolder
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

@Module
class NavigationModule {

    private var cicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @Singleton
    fun navigatorHolder(): NavigatorHolder {
        return cicerone.navigatorHolder
    }

    @Provides
    @Singleton
    fun router(): Router {
        return cicerone.router
    }

    @Provides
    @Singleton
    fun localHolder(): LocalCiceroneHolder {
        return LocalCiceroneHolder()
    }

}