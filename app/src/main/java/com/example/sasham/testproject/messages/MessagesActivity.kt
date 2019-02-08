package com.example.sasham.testproject.messages

import android.graphics.PorterDuff
import android.os.Bundle
import android.text.util.Linkify
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseActivity
import com.example.sasham.testproject.base.BaseDaggerActivity
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.FavoriteTheme
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.util.NetworkUtil
import com.example.sasham.testproject.util.PreferencesHelper
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_messages.*
import kotlinx.android.synthetic.main.theme_item.*
import me.saket.bettermovementmethod.BetterLinkMovementMethod
import javax.inject.Inject

class MessagesActivity : BaseDaggerActivity(), BaseActivity.OnConnectionListener {

    private var snackbar: Snackbar? = null
    private var isConnected = false
    private var messagesFragment: androidx.fragment.app.Fragment? = null
    private var theme: Theme? = null

    @Inject
    lateinit var data: DataRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_messages)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        setDefaultTitle()

        theme = intent.getParcelableExtra(Constants.THEME_MODEL)

        favoriteBtn.setOnClickListener {

            Completable.fromAction {
                if (theme?.isFavorite!!)
                    data.removeFavoriteTheme(FavoriteTheme.copy(theme!!)).blockingAwait()
                else data.addFavoriteTheme(FavoriteTheme.copy(theme!!)).blockingAwait()
            }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .andThen({
                        theme?.isFavorite = theme?.isFavorite?.not()
                        data.favoriteStatusChangeEvent.onNext(theme!!)
                        setFavoriteState()
                    })

                    .subscribe()
        }

        setFavoriteState()
        setThemeReview()

        //Показываем фрагменты только если доступна сеть
        if (NetworkUtil.isConnectedNetwork(this)) {

            showMessagesFragment()
        }

        addOnConnectionListener(this)
        startListenInternetState()
    }

    private fun setFavoriteState() {
        val color = if (theme?.isFavorite!!) R.color.favorite_theme else R.color.non_favorite_theme
        favoriteBtn.drawable.setColorFilter(ContextCompat.getColor(this, color), PorterDuff.Mode.SRC_ATOP)
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

    private fun setThemeReview() {
        val currentTheme = theme

        userName!!.text = currentTheme!!.userName
        topicName!!.text = currentTheme.topicText

        msgText!!.text = StringUtil.stripHtml(currentTheme.msgText)
        msgText!!.movementMethod = BetterLinkMovementMethod.getInstance()
        Linkify.addLinks(msgText!! as TextView, Linkify.WEB_URLS)
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, msgText!!)
                .setOnLinkClickListener { textView, url ->
                    WebActivity.startActivity(url, this@MessagesActivity)
                    true
                }

        if (StringUtil.isNotNullOrEmpty(currentTheme.msgTime)) {
            createdTime!!.text = StringUtil.getDateFromMillis(currentTheme.msgTime!!, Constants.TIME_PATTERN)
        }
    }

    private fun setDefaultTitle() {
        title = ""
    }

    //Показываем список топиков
    private fun showMessagesFragment() {
        messagesFragment = MessagesFragment()

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragmentC, MessagesFragment.newInstance(theme!!))
                .commit()
    }


    override fun internetConnectionChanged(connected: Boolean) {
        isConnected = connected

        if (connected) {
            if (snackbar != null) {
                snackbar!!.dismiss()

                //Если не было сети и не был добавлен фрагмент - добавляем
                if (messagesFragment == null) {
                    showMessagesFragment()
                }
            }

        } else {
            snackbar = Snackbar.make(mainContainer!!, R.string.connection_failed, Snackbar.LENGTH_INDEFINITE)
            snackbar!!.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
