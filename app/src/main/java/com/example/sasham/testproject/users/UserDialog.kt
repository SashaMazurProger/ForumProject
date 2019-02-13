package com.example.sasham.testproject.users

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.base.BaseDialog
import com.example.sasham.testproject.model.User
import com.example.sasham.testproject.util.StringUtil
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user_dialog.*

class UserDialog : BaseDialog(), UserDetailsView {

    @InjectPresenter
    lateinit var presenter: UserDetPresenter

    @ProvidePresenter
    fun presenter(): UserDetPresenter {
        if (arguments!!.containsKey(Constants.USER_MODEL)) {
            return UserDetPresenter(arguments!!.getParcelable(Constants.USER_MODEL))
        }
        return UserDetPresenter(null, arguments!!.getInt(Constants.USER_MODEL_ID))
    }

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

    override val layoutId: Int
        get() = R.layout.user_dialog


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent);

    }

    companion object {

        fun newInstance(user: User): UserDialog {
            val bundle = Bundle()
            bundle.putParcelable(Constants.USER_MODEL, user)
            val dialog = UserDialog()
            dialog.arguments = bundle
            return dialog
        }

        fun newInstance(userId: Int): UserDialog {
            val bundle = Bundle()
            bundle.putInt(Constants.USER_MODEL_ID, userId)
            val dialog = UserDialog()
            dialog.arguments = bundle
            return dialog
        }
    }

}
