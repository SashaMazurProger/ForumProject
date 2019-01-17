package com.example.sasham.testproject.themes

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

import com.example.sasham.testproject.BaseActivity
import com.example.sasham.testproject.BaseDaggerActivity
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.messages.MessagesActivity
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.util.NetworkUtil
import com.example.sasham.testproject.util.PreferencesHelper

import javax.inject.Inject

import butterknife.BindView
import butterknife.ButterKnife
import kotlinx.android.synthetic.main.activity_themes.*

class ThemesActivity : BaseDaggerActivity(), ThemesListingFragment.Callback, BaseActivity.OnConnectionListener {

    private var snackbar: Snackbar? = null
    private var isConnected = false
    private var themesfragment: Fragment? = null

    @Inject
    lateinit var infoRepository: FavoriteThemeInfoRepositoryImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_themes)

        ButterKnife.bind(this)
        setSupportActionBar(toolbar)

        setDefaultTitle()

        //Показываем фрагменты только если доступна сеть
        if (NetworkUtil.isConnectedNetwork(this)) {
            val intent = intent

            if (intent.action == SHOW_MESSAGES_ACTION) {
                //TODO: show message fragment

            } else {
                showThemesFragment()
            }
        }

        addOnConnectionListener(this)
        startListenInternetState()
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
        themesfragment = ThemesListingFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.themes_listing_container, themesfragment)
                .commit()
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

                //Если не было сети и не был добавлен фрагмент - добавляем
                if (themesfragment == null) {
                    showThemesFragment()
                }
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
