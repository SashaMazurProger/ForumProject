package com.example.sasham.testproject.themes

import com.example.sasham.testproject.model.FavoriteTheme
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import com.example.sasham.testproject.R
import kotlinx.android.synthetic.main.favorite_theme_item.view.*

class FavoriteThemeItem(val theme: FavoriteTheme) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title.text = theme.topicText
        viewHolder.itemView.user.text = theme.userName
    }

    override fun getLayout(): Int {
        return R.layout.favorite_theme_item
    }
}