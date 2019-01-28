package com.example.sasham.testproject.themes

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseActivity
import com.example.sasham.testproject.base.BaseDaggerActivity
import com.example.sasham.testproject.users.UsersFragment
import com.example.sasham.testproject.util.PreferencesHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_themes.*


class ThemesActivity : BaseDaggerActivity() {

    private var snackbar: Snackbar? = null
    private var isConnected = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_themes)
        setSupportActionBar(toolbar)
        setDefaultTitle()
        startListenInternetState()
        showThemesFragment()

        bottomNav.setOnNavigationItemSelectedListener {

            setDefaultTitle()
            when (it.itemId) {
                R.id.menu_themes -> {
                    showThemesFragment()
                }
                R.id.menu_users -> {
                    showUsersFragment()
                }

            }
            true
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
        setToolbarTitle(R.string.app_name)
    }

    //Показываем список топиков
    private fun showThemesFragment() {

        val f = supportFragmentManager.findFragmentByTag(ThemesFragment::class.java.simpleName)
        if (f == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, ThemesFragment(), ThemesFragment::class.java.simpleName)
                    .commit()
        } else {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_c, f, ThemesFragment::class.java.simpleName)
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

}
