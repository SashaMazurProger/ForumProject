package com.example.sasham.testproject

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sasham.testproject.dependencies.AppComponent
import com.example.sasham.testproject.dependencies.DaggerAppComponent
import com.example.sasham.testproject.notification.NewMessagesWorker
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    var appComp: AppComponent? = null
        private set


    override fun onCreate() {
        super.onCreate()

        instance = this
        injectDependencies()

//        val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//        val newMessagesWork = PeriodicWorkRequest.Builder(NewMessagesWorker::class.java, 30, TimeUnit.SECONDS)
//                .setConstraints(constraints)
//                .build()
//
//        WorkManager.getInstance()
//                .enqueue(newMessagesWork)
    }

    private fun injectDependencies() {
        appComp = DaggerAppComponent.builder()
                .context(this)
                .build()

        appComp!!.injectApp(this)
    }

    override fun activityInjector(): AndroidInjector<Activity>? {
        return dispatchingAndroidInjector
    }

    companion object {
        var instance: App? = null
            private set
    }

    fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnected
    }
}
