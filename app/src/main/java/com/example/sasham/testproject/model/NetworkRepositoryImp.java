package com.example.sasham.testproject.model;

import com.example.sasham.testproject.network.MessageAnswer;
import com.example.sasham.testproject.network.MessageWraper;
import com.example.sasham.testproject.network.ThemeAnswer;
import com.example.sasham.testproject.network.ThemesWraper;
import com.example.sasham.testproject.network.WebestApi;
import com.example.sasham.testproject.util.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class NetworkRepositoryImp implements NetworkRepository {


    private WebestApi webestApi;

    @Inject
    public NetworkRepositoryImp(WebestApi webestApi) {
        this.webestApi = webestApi;
    }

    @Override
    public Observable<List<Theme>> themes(String page) {
        return webestApi.themes(page)
                .map(new Function<ThemesWraper, List<ThemeAnswer>>() {
                    @Override
                    public List<ThemeAnswer> apply(ThemesWraper themesWraper) throws Exception {
                        return themesWraper.getThemeAnswers();
                    }
                })
                .flatMap(new Function<List<ThemeAnswer>, ObservableSource<List<Theme>>>() {
                    @Override
                    public ObservableSource<List<Theme>> apply(List<ThemeAnswer> themeAnswers) throws Exception {
                        List<Theme> themes = new ArrayList<>();
                        for (ThemeAnswer themeAnswer : themeAnswers) {
                            themes.add(Converter.themeAnswerToTheme(themeAnswer));
                        }
                        return Observable.just(themes);
                    }
                });
    }

    @Override
    public Observable<List<Message>> themeMessages(String themeId) {
        return webestApi
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
