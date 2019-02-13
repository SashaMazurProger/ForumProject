package com.example.sasham.testproject.navigation

import androidx.fragment.app.Fragment
import com.example.sasham.testproject.account.AuthFragment
import com.example.sasham.testproject.themes.ThemesFragment
import com.example.sasham.testproject.users.AccountFragment
import com.example.sasham.testproject.users.UsersFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object ThemesScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ThemesFragment.newInstance()
        }
    }

    object UsersScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return UsersFragment.newInstance()
        }
    }

    object AccountScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AccountFragment.newInstance()
        }
    }

    object AuthScreen : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AuthFragment.newInstance()
        }
    }

    class TabScreen(val tabName: String) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            return TabContainerFragment.getNewInstance(tabName)
        }
    }

    class ForwardScreen(val screenName: String, val data: Any?) : SupportAppScreen() {

        override fun getFragment(): Fragment {
            when (screenName) {
                ThemesScreen.screenKey -> return ThemesScreen.fragment
                UsersScreen.screenKey -> return UsersScreen.fragment
                AuthScreen.screenKey -> return AuthScreen.fragment
                AccountScreen.screenKey -> return AccountScreen.fragment
            }

            return ThemesScreen.fragment
        }
    }
}