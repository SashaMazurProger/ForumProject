package com.example.sasham.testproject.messages;


import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.model.NetworkRepository;
import com.example.sasham.testproject.network.MessageAnswer;
import com.example.sasham.testproject.network.MessageWraper;
import com.example.sasham.testproject.network.WebestApi;
import com.example.sasham.testproject.util.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MessagesListingInteractorImp implements MessagesListingInteractor {

    private NetworkRepository networkRepository;

    @Inject
    public MessagesListingInteractorImp(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    @Override
    public Observable<List<Message>> fetchMessages(String themeId) {
        return networkRepository.themeMessages(themeId);
    }
}
