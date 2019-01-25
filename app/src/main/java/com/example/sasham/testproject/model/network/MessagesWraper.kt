package com.example.sasham.testproject.model.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessagesWraper {
    @SerializedName("objects")
    @Expose
    var messageAnswers: List<MessageAnswer>? = null
}
