package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.NetworkRepository;
import com.example.sasham.testproject.model.Theme;
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

public class ThemesListingInteractorImp implements ThemesListingInteractor {

    private NetworkRepository networkRepository;

    @Inject
    public ThemesListingInteractorImp(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    @Override
    public Observable<List<Theme>> fetchThemes(int page) {

        String pageString = String.valueOf(page);
        return networkRepository.themes(pageString);
    }
}
