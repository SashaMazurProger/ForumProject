package com.example.sasham.testproject

import android.app.Activity
import android.app.Application

import com.example.sasham.testproject.dependencies.AppComponent
import com.example.sasham.testproject.notification.NewMessagesWorker

import java.util.concurrent.TimeUnit

import javax.inject.Inject

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.sasham.testproject.dependencies.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>
    var appComp: AppComponent? = null
        private set


    override fun onCreate() {
        super.onCreate()

        instance = this
        injectDependencies()

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val newMessagesWork = PeriodicWorkRequest.Builder(NewMessagesWorker::class.java, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance()
                .enqueue(newMessagesWork)
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
}
