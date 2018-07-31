package com.example.sasham.testproject.messages;

import com.example.sasham.testproject.model.Message;

import java.util.List;

public interface MessagesListingView {

    void showThemeMessages(List<Message> messageList);
    void onLoading();
    void onError(String message);

}
