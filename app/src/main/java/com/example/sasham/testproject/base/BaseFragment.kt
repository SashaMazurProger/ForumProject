package com.example.sasham.testproject.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import com.example.sasham.testproject.MvpAppCompatFragment


abstract class BaseFragment : MvpAppCompatFragment(), IView {

    @get:Nullable
    var baseActivity: BaseActivity? = null
        protected set

    abstract val layoutId: Int

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is BaseActivity) {
            baseActivity = context
        }

        retainInstance = true
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }


    override fun showLoading() {
        if (activityIsNotNull()) {
            baseActivity!!.showLoading()
        }
    }

    override fun hideLoading() {
        if (activityIsNotNull()) {
            baseActivity!!.hideLoading()
        }
    }

    private fun activityIsNotNull(): Boolean {
        return baseActivity != null
    }

    override fun message(resId: Int) {
        if (activityIsNotNull()) {
            baseActivity!!.message(resId)
        }
    }

    override fun message(message: String) {
        if (activityIsNotNull()) {
            baseActivity!!.message(message)
        }
    }

    override fun hideKeyboard() {
        if (activityIsNotNull()) {
            baseActivity!!.hideKeyboard()
        }
    }

}
