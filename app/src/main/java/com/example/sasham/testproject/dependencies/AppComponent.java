package com.example.sasham.testproject.dependencies;

import android.content.Context;

import com.example.sasham.testproject.App;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = AppModule.class)
public interface AppComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder context(Context context);
        AppComponent build();
    }

    void injectApp(App app);
}
