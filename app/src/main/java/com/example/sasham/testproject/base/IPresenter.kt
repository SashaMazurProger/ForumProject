package com.example.sasham.testproject.base

import android.os.Bundle


import androidx.annotation.NonNull

interface IPresenter<T : IView> {

    fun onAttach(view: T)

    fun onRestoreView(@NonNull savedInstanceState: Bundle)

    fun onSaveStateView(@NonNull savedInstanceState: Bundle)

    fun onDetach()

    fun setUserAsLoggedOut()
}
