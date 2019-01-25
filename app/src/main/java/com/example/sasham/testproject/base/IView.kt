package com.example.sasham.testproject.base

import androidx.annotation.StringRes
import com.arellomobile.mvp.MvpView
import com.example.sasham.testproject.App

interface IView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun message(@StringRes resId: Int)

    fun message(message: String)

    fun hideKeyboard()

}

