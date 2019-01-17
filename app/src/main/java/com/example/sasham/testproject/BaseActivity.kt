package com.example.sasham.testproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sasham.testproject.util.NetworkUtil
import com.example.sasham.testproject.util.PreferencesHelper


abstract class BaseActivity : AppCompatActivity() {

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
}
