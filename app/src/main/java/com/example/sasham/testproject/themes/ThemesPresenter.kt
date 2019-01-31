package com.example.sasham.testproject.themes

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.sasham.testproject.App
import com.example.sasham.testproject.model.DataRepository
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


    fun loadSections() {

        val disposable = data.sections()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ sections ->
                    viewState.showSections(sections)
                },
                        { throwable -> viewState.message(throwable.message!!) })

        compositeDisposable.add(disposable);
    }

    fun selectSection(section: Section) {
        this.section = section
        firstPage()
    }
}
