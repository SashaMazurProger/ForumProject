package com.example.sasham.testproject.users


import android.view.View
import android.widget.TextView
import com.example.sasham.testproject.R

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.user_item.view.*

class UserItem(val user: User) : Item() {


    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.userName.text = user.name
       // viewHolder.name.text = user.name
    }

//    override fun createViewHolder(itemView: View): UserVH {
//        return UserVH(itemView)
//    }

    override fun getLayout(): Int = R.layout.user_item

//    inner class UserVH(val containerView: View) : ViewHolder(containerView) {
//        val name: TextView = containerView.findViewById(R.id.userName)
//    }
}