package com.example.sasham.testproject.themes

import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.FavoriteTheme
import com.example.sasham.testproject.model.Theme
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.favorite_theme_item.view.*

class FavoriteThemeItem(val theme: FavoriteTheme, val presenter: ThemesPresenter) : Item() {

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.title.text = theme.topicText
        viewHolder.itemView.user.text = theme.userName
        viewHolder.itemView.favoriteBtn.setOnClickListener { presenter.onToggleFavoriteState(Theme.copy(theme)) }
    }

    override fun getLayout(): Int {
        return R.layout.favorite_theme_item
    }
}