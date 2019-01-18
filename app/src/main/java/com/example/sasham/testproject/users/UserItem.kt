package com.example.sasham.testproject.users

import android.view.ViewGroup
import android.widget.TextView
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.User
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

class UserItem(val user: User) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        (viewHolder.itemView as ViewGroup).findViewById<TextView>(R.id.userName).text = user.userName

    }

    override fun getLayout(): Int = R.layout.user_item

}