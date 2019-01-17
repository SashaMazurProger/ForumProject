package com.example.sasham.testproject.messages

import com.example.sasham.testproject.model.Message

interface MessagesListingView {

    fun showThemeMessages(messageList: List<Message>)
    fun onLoading()
    fun onError(message: String)

}
