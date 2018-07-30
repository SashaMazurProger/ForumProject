package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThemesListingPresenterImp implements ThemesListingPresenter {

    private ThemesListingView view;
    private ThemesListingInteractor themesListingInteractor;
    private List<Theme> loadedThemes = new ArrayList<>();
    private int currentPage;


    @Inject
    public ThemesListingPresenterImp(ThemesListingInteractor themesListingInteractor) {
        this.themesListingInteractor = themesListingInteractor;
    }

    @Override
    public void setView(ThemesListingView view) {
        this.view = view;
    }

    @Override
    public void firstPage() {
        currentPage = 1;
        loadedThemes.clear();
        loadThemes();
    }

    @Override
    public void nextPage() {
        currentPage++;
        loadThemes();
    }

    @Override
    public void destroy() {
        view = null;
    }

    @Override
    public void loadNewData() {
        firstPage();
    }


    private void loadThemes() {
        view.onLoading();
        themesListingInteractor.fetchThemes(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Theme>>() {
                               @Override
                               public void accept(List<Theme> themes) throws Exception {
                                   loadedThemes.addAll(themes);
                                   displayThemes();
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                view.onError(throwable.getMessage());
                            }
                        });
    }

    private void displayThemes() {
        if (isViewAttached()) {
            view.showThemes(loadedThemes);
        }
    }

    private boolean isViewAttached() {
        return view != null;
    }

}
