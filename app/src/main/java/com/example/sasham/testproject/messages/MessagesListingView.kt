package com.example.sasham.testproject.messages

import com.example.sasham.testproject.base.IView
import com.example.sasham.testproject.model.Message

interface MessagesListingView :IView{

    fun showThemeMessages(messageList: List<Message>)
}
