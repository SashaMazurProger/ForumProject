package com.example.sasham.testproject.users


import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.User

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.user_item.view.*

class UserItem(val user: User) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.userName.text = user.name
    }

    override fun getLayout(): Int = R.layout.user_item
}