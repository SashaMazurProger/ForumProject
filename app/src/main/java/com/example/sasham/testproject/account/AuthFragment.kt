package com.example.sasham.testproject.account

//package com.example.sasham.testproject.users
//
//import android.os.Bundle
//import android.view.View
//import com.arellomobile.mvp.presenter.InjectPresenter
//import com.example.sasham.testproject.R
//import com.example.sasham.testproject.base.BaseFragment
//import kotlinx.android.synthetic.main.auth_fragment.*
//
//class AuthFragment : BaseFragment(), AuthView {
//
//    @InjectPresenter
//    lateinit var presenter: AuthPresenter
//
//    override val layoutId: Int
//        get() = R.layout.auth_fragment
//
//    override fun onRegistration() {
//
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        login_btn.setOnClickListener {
//            presenter.onLoginClicked(email_et.text.toString(), password_et.text.toString())
//        }
//    }
//
//    companion object {
//
//        fun newInstance(): AuthFragment {
//            return AuthFragment()
//        }
//    }
//
//}
