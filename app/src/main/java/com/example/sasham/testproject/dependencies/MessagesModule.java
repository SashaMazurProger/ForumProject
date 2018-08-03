package com.example.sasham.testproject.dependencies;

import com.example.sasham.testproject.messages.MessagesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public interface MessagesModule {
    @MessagesListingFragmentScope
    @ContributesAndroidInjector(modules = MessagesListingFragmentModule.class)
    MessagesFragment messagesFragmentInjector();
}
