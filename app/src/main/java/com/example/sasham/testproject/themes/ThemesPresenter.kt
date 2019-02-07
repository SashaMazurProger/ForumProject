package com.example.sasham.testproject.themes

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.FavoriteTheme
import com.example.sasham.testproject.model.Section
import com.example.sasham.testproject.model.Theme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class ThemesPresenter : MvpPresenter<ThemesView>() {

    @Inject
    lateinit var data: DataRepository

    private val loadedThemes = ArrayList<Theme>()
    private val compositeDisposable = CompositeDisposable()
    private var currentPage: Int = 0
    private var section: Section? = null

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        firstPage()
        loadSections()
        loadFavoriteThemes()
    }

    fun firstPage() {
        currentPage = 1
        loadedThemes.clear()
        loadThemes()
    }

    fun nextPage() {
        currentPage++
        loadThemes()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    fun loadNewData() {
        firstPage()
    }


    private fun loadThemes() {

        viewState.showLoading()

        val disposable = data.themes(currentPage.toString(), section)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ themes ->
                    viewState.hideLoading()
                    loadedThemes.addAll(themes)
                    viewState.showThemes(loadedThemes)
                },
                        { throwable ->
                            viewState.hideLoading()
                            viewState.message(throwable.message!!)
                        })
        compositeDisposable.add(disposable)

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

        if (!theme.isFavorite!!) {
            data.addFavoriteTheme(FavoriteTheme.copy(theme))
                    .andThen({ loadFavoriteThemes() })
                    .andThen({
                        //                        theme.isFavorite = theme.isFavorite!!.not()
                        loadedThemes.find { it.id == theme.id }
                                .let { it?.isFavorite = it?.isFavorite?.not() }

                        viewState.showThemes(loadedThemes)
                    })
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()

        } else {
            data.removeFavoriteTheme(FavoriteTheme.copy(theme))
                    .andThen({ loadFavoriteThemes() })
                    .andThen({
                        //                        theme.isFavorite = theme.isFavorite!!.not()
//                        viewState.updateTheme(theme)

                        loadedThemes.find { it.id == theme.id }
                                .let { it?.isFavorite = it?.isFavorite?.not() }

                        viewState.showThemes(loadedThemes)
                    })

                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe()
        }
    }
}
