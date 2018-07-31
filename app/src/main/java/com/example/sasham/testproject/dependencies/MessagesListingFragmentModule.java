package com.example.sasham.testproject.dependencies;

import com.example.sasham.testproject.messages.MessagesListingInteractor;
import com.example.sasham.testproject.messages.MessagesListingInteractorImp;
import com.example.sasham.testproject.messages.MessagesListingPresenter;
import com.example.sasham.testproject.messages.MessagesListingPresenterImp;

import dagger.Binds;
import dagger.Module;

@Module
public interface MessagesListingFragmentModule {

    @MessagesListingFragmentScope
    @Binds
    MessagesListingInteractor messagesListingInteractor(MessagesListingInteractorImp messagesListingInteractorImp);

    @MessagesListingFragmentScope
    @Binds
    MessagesListingPresenter messagesListingPresenter(MessagesListingPresenterImp messagesListingPresenterImp);

}
