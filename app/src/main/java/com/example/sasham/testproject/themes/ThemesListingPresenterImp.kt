package com.example.sasham.testproject.themes

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.Theme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class ThemesListingPresenterImp : MvpPresenter<ThemesListingView>(), ThemesListingPresenter {

    @Inject
    lateinit var data: DataRepository

    private val loadedThemes = ArrayList<Theme>()
    private val compositeDisposable = CompositeDisposable()
    private var currentPage: Int = 0

    init {
        App.instance!!.appComp!!.inject(this)
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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun loadNewData() {
        firstPage()
    }


    private fun loadThemes() {

        val disposable = data.themes(currentPage.toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ themes ->
                    loadedThemes.addAll(themes)
                    viewState.showThemes(loadedThemes)
                },
                        { throwable -> viewState.message(throwable.message) })

        compositeDisposable.add(disposable);
    }
}
