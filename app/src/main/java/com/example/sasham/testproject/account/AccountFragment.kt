package com.example.sasham.testproject.users

import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment

class AccountFragment : BaseFragment(), AccountView {

    @InjectPresenter
    lateinit var presenter: AccountPresenter

    override val layoutId: Int
        get() = R.layout.account_fragment

    override fun onRegistration() {

    }

    override fun onLogin() {

    }

    companion object {

        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

}
