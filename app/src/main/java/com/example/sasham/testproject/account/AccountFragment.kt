package com.example.sasham.testproject.users

import android.os.Bundle
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseFragment
import com.example.sasham.testproject.model.User
import com.example.sasham.testproject.util.StringUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.account_fragment.*

class AccountFragment : BaseFragment(), AccountView {


    @InjectPresenter
    lateinit var presenter: AccountPresenter

    @ProvidePresenter
    fun provide(): AccountPresenter {
        return AccountPresenter()
    }

    override val layoutId: Int
        get() = R.layout.account_fragment


    override fun showUser(user: User) {
        Picasso.get().load(Constants.WEBEST_BASE_AVATAR_URL.plus(user.avatar))
                .error(R.mipmap.ic_avatar)
                .into(userAvatar)

        userName.text = user.userName
        userLocation.text = "${user.city} ${user.country}"
        userBirthday.text = user.birthday
        userEmail.text = user.email
        userRegistration.text = StringUtil.getDateFromMillis(user.regDate.toString(), Constants.TIME_PATTERN)
    }

    companion object {

        fun newInstance(): AccountFragment {
            val f = AccountFragment()
            return f
        }
    }

}
