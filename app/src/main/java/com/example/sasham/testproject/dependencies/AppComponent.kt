package com.example.sasham.testproject.dependencies

import android.content.Context

import com.example.sasham.testproject.App
import com.example.sasham.testproject.notification.NewMessagesWorker
import com.example.sasham.testproject.users.UsersFragment

import javax.inject.Singleton

import dagger.BindsInstance
import dagger.Component

@Singleton
@Component(modules = arrayOf(AppModule::class, WebestApiModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun injectApp(app: App)

    fun inject(newMessagesWorker: NewMessagesWorker)
    fun injectF(f: UsersFragment)
}
