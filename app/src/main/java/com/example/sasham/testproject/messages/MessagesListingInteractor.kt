package com.example.sasham.testproject.messages

import com.example.sasham.testproject.model.Message

import io.reactivex.Observable

interface MessagesListingInteractor {

    fun fetchMessages(themeId: String): Observable<List<Message>>
}
