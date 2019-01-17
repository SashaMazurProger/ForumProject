package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.Theme

import java.util.ArrayList

import javax.inject.Inject

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class ThemesListingPresenterImp @Inject
constructor(private val themesListingInteractor: ThemesListingInteractor) : ThemesListingPresenter {

    private var view: ThemesListingView? = null
    private val loadedThemes = ArrayList<Theme>()
    private val compositeDisposable = CompositeDisposable()
    private var currentPage: Int = 0

    private val isViewAttached: Boolean
        get() = view != null

    override fun setView(view: ThemesListingView) {
        this.view = view
    }

    override fun firstPage() {
        currentPage = 1
        loadedThemes.clear()
        loadThemes()
    }

    override fun nextPage() {
        currentPage++
        loadThemes()
    }

    override fun destroy() {
        view = null
        compositeDisposable.clear()
    }

    override fun loadNewData() {
        firstPage()
    }


    private fun loadThemes() {
        view!!.onLoading()
        val disposable = themesListingInteractor.fetchThemes(currentPage)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ themes ->
                    loadedThemes.addAll(themes)
                    displayThemes()
                },
                        { throwable -> view!!.onError(throwable.message!!) })

                compositeDisposable.add(disposable);
    }

    private fun displayThemes() {
        if (isViewAttached) {
            view!!.showThemes(loadedThemes)
        }
    }

}
