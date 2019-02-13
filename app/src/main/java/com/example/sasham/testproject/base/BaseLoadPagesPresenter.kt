package com.example.sasham.testproject.base


import io.reactivex.disposables.Disposable
import java.util.*

abstract class BaseLoadPagesPresenter<I, T : IView> : BasePresenter<T>() {

    protected var isLoading: Boolean = false
    protected var page: Int = 1
    protected var cachedItems: MutableList<I> = ArrayList()
    protected var pageDisposable: Disposable? = null

    protected val canLoadNextPage: Boolean
        get() = !isLoading

    init {
        initStartState()
    }

    protected fun onSuccessLoadPage() {
        isLoading = false
    }

    protected fun initStartState() {
        pageDisposable?.dispose()
        page = 1
        isLoading = false
        cachedItems.clear()
    }


    protected fun onErrorLoadPage() {
        isLoading = false
    }

    protected abstract fun loadPage(page: Int): Disposable?


    fun firstPage() {
        initStartState()
        pageDisposable = loadPage(page)
    }

    fun nextPage() {
        if (canLoadNextPage) {
            page += 1
            pageDisposable = loadPage(page)
        }
    }

    fun onRefresh() {
        firstPage()
    }

    companion object {
        val TAG = BaseLoadPagesPresenter::class.java.simpleName
    }
}