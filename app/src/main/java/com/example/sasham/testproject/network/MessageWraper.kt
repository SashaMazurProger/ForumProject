package com.example.sasham.testproject.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MessageWraper {
    @SerializedName("users")
    @Expose
    var messageAnswers: List<MessageAnswer>? = null
}
