package com.example.sasham.testproject;

import android.app.Activity;
import android.app.Application;

import com.example.sasham.testproject.dependencies.AppComponent;
import com.example.sasham.testproject.dependencies.DaggerAppComponent;
import com.example.sasham.testproject.notification.NewMessagesWorker;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    private AppComponent appComp;
    private static App app;


    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        injectDependencies();

        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest newMessagesWork = new PeriodicWorkRequest
                .Builder(NewMessagesWorker.class, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance()
                .enqueue(newMessagesWork);
    }

    private void injectDependencies() {
        appComp = DaggerAppComponent.builder()
                .context(this)
                .build();

        appComp.injectApp(this);
    }

    public AppComponent getAppComp() {
        return appComp;
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static App getInstance() {
        return app;
    }
}
