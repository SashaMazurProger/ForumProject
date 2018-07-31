package com.example.sasham.testproject.dependencies;

import android.content.Context;

import com.example.sasham.testproject.App;
import com.example.sasham.testproject.network.WebestApi;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, WebestApiModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder context(Context context);
        AppComponent build();
    }

    void injectApp(App app);
}
