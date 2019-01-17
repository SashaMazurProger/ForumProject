package com.example.sasham.testproject.dependencies

import com.example.sasham.testproject.messages.MessagesListingInteractor
import com.example.sasham.testproject.messages.MessagesListingInteractorImp
import com.example.sasham.testproject.messages.MessagesListingPresenter
import com.example.sasham.testproject.messages.MessagesListingPresenterImp

import dagger.Binds
import dagger.Module

@Module
interface MessagesListingFragmentModule {

    @MessagesListingFragmentScope
    @Binds
    fun messagesListingInteractor(messagesListingInteractorImp: MessagesListingInteractorImp): MessagesListingInteractor

    @MessagesListingFragmentScope
    @Binds
    fun messagesListingPresenter(messagesListingPresenterImp: MessagesListingPresenterImp): MessagesListingPresenter

}
