package com.example.sasham.testproject.base

import androidx.annotation.StringRes

interface IView {

    fun isNetworkConnected(): Boolean

    fun showLoading()

    fun hideLoading()

    fun showMessage(@StringRes resId: Int)

    fun showMessage(message: String)

    fun hideKeyboard()

}

