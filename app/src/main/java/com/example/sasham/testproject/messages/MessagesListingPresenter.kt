package com.example.sasham.testproject.messages

interface MessagesListingPresenter {

    fun setView(view: MessagesListingView)
    fun fetchMessages(themeId: String)
    fun destroy()
    fun loadNewData(themeId: String)
}
