package com.example.sasham.testproject.themes

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseActivity
import com.example.sasham.testproject.base.BaseDaggerActivity
import com.example.sasham.testproject.messages.MessagesActivity
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.users.UsersFragment
import com.example.sasham.testproject.util.PreferencesHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_themes.*
import javax.inject.Inject


class ThemesActivity : BaseDaggerActivity(), ThemesListingFragment.Callback, BaseActivity.OnConnectionListener {

    private var snackbar: Snackbar? = null
    private var isConnected = false

    @Inject
    lateinit var infoRepository: FavoriteThemeInfoRepositoryImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_themes)
        setSupportActionBar(toolbar)
        setDefaultTitle()
        addOnConnectionListener(this)
        startListenInternetState()
        showThemesFragment()

        (bottomNav as BottomNavigationView).setOnNavigationItemSelectedListener(
                {
                    when (it.itemId) {
                        R.id.menu_themes -> {
                            showThemesFragment()
                        }
                        R.id.menu_users -> {
                            showUsersFragment()
                        }

                    }
                    true
                })
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.themes_menu, menu)

        val theme = menu.findItem(R.id.action_black_theme)
        theme.isChecked = PreferencesHelper.getTheme(applicationContext) == Constants.THEME_BLACK

        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        //Изменяем основную тему приложения
        if (item.itemId == R.id.action_black_theme) {

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

        return super.onOptionsItemSelected(item)
    }

    private fun setDefaultTitle() {
        title = getString(R.string.app_name)
    }

    //Показываем список топиков
    private fun showThemesFragment() {

        val f = supportFragmentManager.findFragmentByTag(ThemesListingFragment::class.java.simpleName)
        if (f == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, ThemesListingFragment(), ThemesListingFragment::class.java.simpleName)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, f, ThemesListingFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun showUsersFragment() {

        val f = supportFragmentManager.findFragmentByTag(UsersFragment::class.java.simpleName)
        if (f == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, UsersFragment(), UsersFragment::class.java.simpleName)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, f, UsersFragment::class.java.simpleName)
                    .commit()
        }
    }

    //Показываем сообщения топика, добавляя новый фрагмент в бэкстек
    override fun onThemeClicked(theme: Theme) {
        infoRepository!!.addLastViewedTheme(theme)
        val intent = Intent(this, MessagesActivity::class.java)
        intent.putExtra(Constants.THEME_MODEL, theme)
        startActivity(intent)
    }


    override fun internetConnectionChanged(connected: Boolean) {
        isConnected = connected

        if (connected) {
            if (snackbar != null) {
                snackbar!!.dismiss()
            }

        } else {
            snackbar = Snackbar.make(mainContainer!!, R.string.connection_failed, Snackbar.LENGTH_INDEFINITE)
            snackbar!!.show()
        }
    }

    companion object {

        val SHOW_MESSAGES_ACTION = "show_messages"
    }

}
