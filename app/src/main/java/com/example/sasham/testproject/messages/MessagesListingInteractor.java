package com.example.sasham.testproject.messages;

import com.example.sasham.testproject.model.Message;

import java.util.List;

import io.reactivex.Observable;

public interface MessagesListingInteractor {

    Observable<List<Message>> fetchMessages(String themeId);
}
