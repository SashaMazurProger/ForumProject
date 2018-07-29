package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.network.ApiBuilder;
import com.example.sasham.testproject.network.ThemeAnswer;
import com.example.sasham.testproject.network.ThemesWraper;
import com.example.sasham.testproject.util.Converter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class ThemesListingInteractorImp implements ThemesListingInteractor {

    @Inject
    public ThemesListingInteractorImp() {
    }

    @Override
    public Observable<List<Theme>> fetchThemes(int page) {

        String pageString = String.valueOf(page);
        return ApiBuilder.createWebestApi()
                .themes(pageString)
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
}
