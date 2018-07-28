package com.example.sasham.testproject.themes;

import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ThemesListingPresenterImp implements ThemesListingPresenter {

    private ThemesListingView view;
    private ThemesListingInteractor themesListingInteractor = new ThemesListingInteractorImp();
    private List<Theme> loadedThemes = new ArrayList<>();
    private int currentPage;


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
