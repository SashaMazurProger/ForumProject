package com.example.sasham.testproject.dependencies

import android.content.Context
import com.example.sasham.testproject.App
import com.example.sasham.testproject.account.AuthPresenter
import com.example.sasham.testproject.messages.MessagesPresenter
import com.example.sasham.testproject.navigation.TabContainerFragment
import com.example.sasham.testproject.notification.NewMessagesWorker
import com.example.sasham.testproject.themes.MainPresenter
import com.example.sasham.testproject.themes.ThemesPresenter
import com.example.sasham.testproject.users.AccountPresenter
import com.example.sasham.testproject.users.UserDetPresenter
import com.example.sasham.testproject.users.UsersPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DataModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

    fun injectApp(app: App)

    fun inject(newMessagesWorker: NewMessagesWorker)
    fun inject(presenter: UsersPresenter)
    fun inject(presenter: ThemesPresenter)
    fun inject(presenter: MessagesPresenter)
    fun inject(presenter: UserDetPresenter)
    fun inject(presenter: AuthPresenter)
    fun inject(presenter: AccountPresenter)
    fun inject(mainPresenter: MainPresenter)
    fun inject(tabContainerFragment: TabContainerFragment)
}
