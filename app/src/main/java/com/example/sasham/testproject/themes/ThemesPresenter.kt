package com.example.sasham.testproject.themes

import com.arellomobile.mvp.InjectViewState
import com.example.sasham.testproject.App
import com.example.sasham.testproject.base.BaseLoadPagesPresenter
import com.example.sasham.testproject.model.FavoriteTheme
import com.example.sasham.testproject.model.Section
import com.example.sasham.testproject.model.Theme
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

@InjectViewState
class ThemesPresenter : BaseLoadPagesPresenter<Theme, ThemesView>() {


    private var section: Section? = null

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        firstPage()
        loadSections()
        loadFavoriteThemes()

        data.favoriteStatusChangeEvent
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ changed: Theme ->

                    loadFavoriteThemes()
                    cachedItems.find { it.id == changed.id }
                            .let { it?.isFavorite = changed?.isFavorite }

                    viewState.showThemes(cachedItems)

                }, {})
    }

    override fun loadPage(page: Int): Disposable {
        return loadThemes(page)
    }

    private fun loadThemes(currentPage: Int): Disposable {

        viewState.showLoading()

        val disposable = data.themes(currentPage.toString(), section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ themes ->
                    onSuccessLoadPage()
                    viewState.hideLoading()
                    cachedItems.addAll(themes)
                    viewState.showThemes(cachedItems)
                },
                        { throwable ->
                            onErrorLoadPage()
                            viewState.hideLoading()
                            viewState.message(throwable.message!!)
                        })

        return disposable

    }


    private fun loadSections() {

        val disposable = data.sections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sections ->
                    viewState.showSections(sections)
                },
                        { throwable -> viewState.message(throwable.message!!) })

        compositeDisposable.add(disposable);
    }

    private fun loadFavoriteThemes() {

        val disposable = data.favoriteThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ themes ->
                    viewState.showFavoriteThemes(themes)
                },
                        { throwable ->
                            viewState.message(throwable.message!!)
                        })

        compositeDisposable.add(disposable)
    }

    fun selectSection(section: Section) {
        this.section = section
        firstPage()
    }

    fun selectFavoriteTheme(theme: FavoriteTheme) {
        viewState.openTheme(Theme.copy(theme))
    }

    fun selectTheme(theme: Theme) {
        viewState.openTheme(theme)
    }

    fun onToggleFavoriteState(theme: Theme) {

        val favorite = FavoriteTheme.copy(theme)

        Completable.fromAction {
            if (theme.isFavorite!!) {
                data.removeFavoriteTheme(FavoriteTheme.copy(theme))
                        .blockingAwait()
            } else {
                favorite.lastTimeViewedInMillis = Calendar.getInstance().timeInMillis
                data.addFavoriteTheme(favorite)
                        .blockingAwait()
            }
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .andThen({
                    loadFavoriteThemes()
                    cachedItems.find { it.id == theme.id }
                            .let { it?.isFavorite = it?.isFavorite?.not() }

                    viewState.showThemes(cachedItems)
                })
                .subscribe()

    }
}
