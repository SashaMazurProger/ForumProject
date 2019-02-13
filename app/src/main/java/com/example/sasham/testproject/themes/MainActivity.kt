package com.example.sasham.testproject.themes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseDaggerActivity
import com.example.sasham.testproject.navigation.BackButtonListener
import com.example.sasham.testproject.navigation.RouterProvider
import com.example.sasham.testproject.navigation.Screens
import com.example.sasham.testproject.util.PreferencesHelper
import kotlinx.android.synthetic.main.activity_main.*
import ru.terrakok.cicerone.Router
import javax.inject.Inject


class MainActivity : BaseDaggerActivity(), MainView, RouterProvider {

    override val router: Router
        get() = globalRouter

    @InjectPresenter
    lateinit var presenter: MainPresenter

    @ProvidePresenter
    fun presenter(): MainPresenter {
        return MainPresenter(globalRouter)
    }

    @Inject
    lateinit var globalRouter: Router

//    @Inject
//    lateinit var navigatorHolder: NavigatorHolder

//    val navigator = object : SupportAppNavigator(this, R.id.fragmentC) {
//        override fun backToUnexisting(screen: SupportAppScreen?) {
//            applyCommand(Forward(screen))
//        }
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        setDefaultTitle()
        startListenInternetState()

        bottomNav.setOnNavigationItemSelectedListener {

            setDefaultTitle()
            when (it.itemId) {
                R.id.menu_themes -> {
                    presenter.showThemesScreenClicked()
                }
                R.id.menu_users -> {
                    presenter.showUsersScreenClicked()
                }
                R.id.menu_account -> {
                    presenter.showAccountScreenClicked()
                }

            }
            true
        }

        if (savedInstanceState == null) {
            selectTab(Screens.ThemesScreen.screenKey)
            bottomNav.selectedItemId = R.id.menu_themes
        }
    }

    override fun selectTab(tab: String) {
        val fm = supportFragmentManager
        var currentFragment: Fragment? = null
        val fragments = fm.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f.isVisible) {
                    currentFragment = f
                    break
                }
            }
        }
        val newFragment = fm.findFragmentByTag(tab)

        if (currentFragment != null && newFragment != null && currentFragment === newFragment) return

        val transaction = fm.beginTransaction()
        if (newFragment == null) {
            transaction.add(R.id.fragmentC, Screens.TabScreen(tab).fragment, tab)
        }

        if (currentFragment != null) {
            transaction.hide(currentFragment)
        }

        if (newFragment != null) {
            transaction.show(newFragment)
        }
        transaction.commitNow()
    }

    override fun onBackPressed() {
        val fm = supportFragmentManager
        var fragment: Fragment? = null
        val fragments = fm.fragments
        if (fragments != null) {
            for (f in fragments) {
                if (f.isVisible) {
                    fragment = f
                    break
                }
            }
        }
        if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            presenter.onBackPressed()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.themes_menu, menu)

        val theme = menu.findItem(R.id.action_black_theme)
        theme.isChecked = PreferencesHelper.getTheme(applicationContext) == Constants.THEME_BLACK

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //Изменяем основную тему приложения
        when (item.itemId) {
            R.id.action_black_theme -> {
                val isChecked = !item.isChecked
                item.isChecked = isChecked

                if (isChecked) {
                    PreferencesHelper.setTheme(applicationContext, Constants.THEME_BLACK)
                } else {
                    PreferencesHelper.setTheme(applicationContext, Constants.THEME_WHITE)
                }
                updateTheme()
                recreateActivity()
            }
            R.id.action_logout -> presenter.logout()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setDefaultTitle() {
        setToolbarTitle(R.string.app_name)
    }

    override fun showLoading() {
        mainProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        mainProgress.visibility = View.GONE
    }

}
