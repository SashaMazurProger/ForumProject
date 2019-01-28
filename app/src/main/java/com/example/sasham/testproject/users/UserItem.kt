package com.example.sasham.testproject.users


import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.Shape
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.User
import com.squareup.picasso.Picasso

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.user_item.view.*
import android.graphics.drawable.shapes.RoundRectShape



class UserItem(val user: User) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.userName.text = user.userName
        Picasso.get().load(Constants.WEBEST_BASE_AVATAR_URL.plus(user.avatar))
                .error(R.mipmap.ic_avatar)
                .into(viewHolder.itemView.userAvatar)

        viewHolder.itemView.userLocation.text = "${user.city} ${user.country}"
        viewHolder.itemView.userMessages.text = user.msgCount.toString()
    }

    override fun getLayout(): Int = R.layout.user_item
}