package com.example.sasham.testproject.account

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import com.example.sasham.testproject.navigation.RouterProvider
import kotlinx.android.synthetic.main.auth_fragment.*

class AuthFragment : BaseFragment(), AuthView {

    @InjectPresenter
    lateinit var presenter: AuthPresenter

    override val layoutId: Int
        get() = R.layout.auth_fragment

    @ProvidePresenter
    fun presenter():AuthPresenter{
        return AuthPresenter((parentFragment as RouterProvider).router)
    }

    override fun onRegistration() {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_btn.setOnClickListener {
            presenter.onLoginClicked(email_et.text.toString(), password_et.text.toString())
        }
    }


    companion object {

        fun newInstance(): AuthFragment {
            return AuthFragment()
        }
    }

}
