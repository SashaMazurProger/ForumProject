package com.example.sasham.testproject.messages


import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.model.NetworkRepository
import com.example.sasham.testproject.network.MessageAnswer
import com.example.sasham.testproject.network.MessageWraper
import com.example.sasham.testproject.network.WebestApi
import com.example.sasham.testproject.util.Converter

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function

class MessagesListingInteractorImp @Inject
constructor(private val networkRepository: NetworkRepository) : MessagesListingInteractor {

    override fun fetchMessages(themeId: String): Observable<List<Message>> {
        return networkRepository.themeMessages(themeId)
    }
}
