package com.example.sasham.testproject.messages


import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.DataRepository

import javax.inject.Inject

import io.reactivex.Observable

class MessagesListingInteractorImp @Inject
constructor(private val dataRepository: DataRepository) : MessagesListingInteractor {

    override fun fetchMessages(themeId: String): Observable<List<Message>> {
        return dataRepository.themeMessages(themeId)
    }
}
