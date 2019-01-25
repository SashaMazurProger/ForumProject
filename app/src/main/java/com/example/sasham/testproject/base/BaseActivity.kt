package com.example.sasham.testproject.base

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.MvpAppCompatActivity
import com.example.sasham.testproject.R
import com.example.sasham.testproject.util.NetworkUtil
import com.example.sasham.testproject.util.PreferencesHelper
import com.example.sasham.testproject.util.isOnline
import com.google.android.material.snackbar.Snackbar


abstract class BaseActivity : MvpAppCompatActivity(), IView {

    private var internetBroadcastReceiver: BroadcastReceiver? = null


    private var onConnectionListeners: MutableList<OnConnectionListener> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        updateTheme()
    }

    fun updateTheme() {
        if (PreferencesHelper.getTheme(applicationContext) == Constants.THEME_BLACK) {
            setTheme(R.style.AppTheme_Black)
        } else {
            setTheme(R.style.AppTheme_White)
        }
    }

    fun recreateActivity() {
        val intent = intent
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    fun addOnConnectionListener(onConnectionListener: OnConnectionListener) {
        this.onConnectionListeners.add(onConnectionListener)
    }

    fun startListenInternetState() {
        if (onConnectionListeners.size == 0) {
            return
        }
        callConnectionListeners(NetworkUtil.isConnectedNetwork(this))

        internetBroadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                callConnectionListeners(NetworkUtil.isConnectedNetwork(context))
            }
        }

        registerReceiver(internetBroadcastReceiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    private fun callConnectionListeners(connectedNetwork: Boolean) {
        for (onConnectionListener in onConnectionListeners) {
            onConnectionListener.internetConnectionChanged(connectedNetwork)
        }
    }


    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDestroy() {
        super.onDestroy()
        if (internetBroadcastReceiver != null) {
            unregisterReceiver(internetBroadcastReceiver)
        }
    }

    fun removeOnConnectionListener(onConnectionListener: OnConnectionListener) {
        onConnectionListeners.remove(onConnectionListener)
    }

    interface OnConnectionListener {
        fun internetConnectionChanged(connected: Boolean)
    }

    private fun showSnackbar(message: String) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)

        // Get the Snackbar's layout view
        val layout = snackbar.view as Snackbar.SnackbarLayout
        layout.setBackgroundColor(ContextCompat.getColor(this, R.color.md_white_1000))
        snackbar.show()
    }

    override fun message(resId: Int) {
        message(getString(resId))
    }

    override fun message(message: String) {
        if (message != null) {
            showSnackbar(message)
        }
    }

    override fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val manager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }

    private fun changeToolbarTitle(title: String, subtitle: String?) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setTitle(title)
            actionBar.setSubtitle(subtitle)
        }
    }

    fun setToolbarTitle(title: String) {
        changeToolbarTitle(title, null)
    }

    fun setToolbarTitle(@StringRes titleResId: Int, @StringRes subtitleResId: Int) {
        val title = getString(titleResId)
        val subtitle = getString(subtitleResId)
        changeToolbarTitle(title, subtitle)
    }

    fun setToolbarTitle(@StringRes titleResId: Int) {
        val title = getString(titleResId)
        changeToolbarTitle(title, null)
    }

    companion object {

        fun <T : Activity> startActivity(context: Context, tClass: Class<T>, bundle: Bundle) {
            val intent = Intent(context, tClass)
            intent.putExtras(bundle)
            context.startActivity(intent)
        }

        fun <T : Activity> startActivity(context: Context, tClass: Class<T>) {
            val intent = Intent(context, tClass)
            context.startActivity(intent)
        }
    }
}
