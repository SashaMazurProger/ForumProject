package com.example.sasham.testproject.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MessageWraper {
    @SerializedName("objects")
    @Expose
    private List<MessageAnswer> messageAnswers;

    public List<MessageAnswer> getMessageAnswers() {
        return messageAnswers;
    }

    public void setMessageAnswers(List<MessageAnswer> messageAnswers) {
        this.messageAnswers = messageAnswers;
    }
}
