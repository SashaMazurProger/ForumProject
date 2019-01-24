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

    override fun showMessage(resId: Int) {
        if (activityIsNotNull()) {
            baseActivity!!.showMessage(resId)
        }
    }

    override fun showMessage(message: String) {
        if (activityIsNotNull()) {
            baseActivity!!.showMessage(message)
        }
    }

    override fun isNetworkConnected(): Boolean {
        return if (activityIsNotNull()) {
            baseActivity!!.isNetworkConnected()
        } else false
    }

    override fun hideKeyboard() {
        if (activityIsNotNull()) {
            baseActivity!!.hideKeyboard()
        }
    }

}
