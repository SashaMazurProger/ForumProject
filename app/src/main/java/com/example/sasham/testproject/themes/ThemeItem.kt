package com.example.sasham.testproject.themes

import android.graphics.PorterDuff
import android.text.util.Linkify
import androidx.core.content.ContextCompat
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.theme_item.view.*
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class ThemeItem(val theme: Theme, val presenter: ThemesPresenter) : Item() {

    override fun bind(holder: ViewHolder, position: Int) {

        holder.itemView.userName!!.text = theme.userName
        holder.itemView.topicName!!.text = theme.topicText

        holder.itemView.msgText!!.text = StringUtil.stripHtml(theme.msgText)
        holder.itemView.msgText!!.movementMethod = BetterLinkMovementMethod.getInstance()
        Linkify.addLinks(holder.itemView.msgText!!, Linkify.WEB_URLS)
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, holder.itemView.msgText!!)
                .setOnLinkClickListener { textView, url ->
                    WebActivity.startActivity(url, holder.itemView.context)
                    true
                }

        if (StringUtil.isNotNullOrEmpty(theme.msgTime)) {
            holder.itemView.createdTime!!.text = StringUtil.getDateFromMillis(theme.msgTime!!, Constants.TIME_PATTERN)
        }

        holder.itemView.favoriteBtn.setOnClickListener { presenter.onToggleFavoriteState(theme) }

        val color = if (theme.isFavorite!!) R.color.favorite_theme else R.color.non_favorite_theme
        holder.itemView.favoriteBtn.drawable.setColorFilter(ContextCompat.getColor(holder.itemView.context, color), PorterDuff.Mode.SRC_ATOP)
    }

    override fun getLayout(): Int {
        return R.layout.theme_item
    }
}