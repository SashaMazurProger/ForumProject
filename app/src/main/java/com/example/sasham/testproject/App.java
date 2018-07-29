package com.example.sasham.testproject;

import android.app.Activity;
import android.app.Application;

import com.example.sasham.testproject.dependencies.AppComponent;
import com.example.sasham.testproject.dependencies.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {
        DaggerAppComponent.builder()
                .context(this)
                .build()
                .injectApp(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
