package com.example.sasham.testproject.messages;


import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.network.ApiBuilder;
import com.example.sasham.testproject.network.MessageAnswer;
import com.example.sasham.testproject.network.MessageWraper;
import com.example.sasham.testproject.util.Converter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class MessagesListingInteractorImp implements MessagesListingInteractor {
    @Override
    public Observable<List<Message>> fetchMessages(String themeId) {
        return ApiBuilder.createWebestApi()
                .themeMessages(themeId)
                .map(new Function<MessageWraper, List<MessageAnswer>>() {
                    @Override
                    public List<MessageAnswer> apply(MessageWraper messageWraper) throws Exception {
                        return messageWraper.getMessageAnswers();
                    }
                })
                .flatMap(new Function<List<MessageAnswer>, ObservableSource<List<Message>>>() {
                    @Override
                    public ObservableSource<List<Message>> apply(List<MessageAnswer> messageAnswers) throws Exception {
                        List<Message> messages = new ArrayList<>();
                        for (MessageAnswer themeAnswer : messageAnswers) {
                            messages.add(Converter.messageAnswerToMessage(themeAnswer));
                        }
                        return Observable.just(messages);
                    }
                });
    }
}
