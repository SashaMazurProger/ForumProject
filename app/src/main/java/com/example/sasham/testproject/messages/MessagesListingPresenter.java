package com.example.sasham.testproject.messages;

public interface MessagesListingPresenter {

    void setView(MessagesListingView view);
    void fetchMessages(String themeId);
    void destroy();
}
