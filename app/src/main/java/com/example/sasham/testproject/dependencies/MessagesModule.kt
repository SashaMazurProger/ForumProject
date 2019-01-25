package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.messages.MessagesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MessagesModule {

    @PerFragment
    @ContributesAndroidInjector(modules = arrayOf(MessagesFragmentModule::class))
    fun messagesFragmentInjector(): MessagesFragment
}
