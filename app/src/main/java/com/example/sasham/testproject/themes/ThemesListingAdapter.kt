package com.example.sasham.testproject.themes

import android.support.v7.widget.RecyclerView
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class ThemesListingAdapter(private val themes: MutableList<Theme>, private val themesListingView: ThemesListingView) : RecyclerView.Adapter<ThemesListingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.theme_listing_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        val currentTheme = themes[position]

        holder.userName!!.text = currentTheme.userName
        holder.forumName!!.text = currentTheme.forumName
        holder.topicName!!.text = currentTheme.topicText

        holder.msgText!!.text = StringUtil.stripHtml(currentTheme.msgText)
        holder.msgText!!.movementMethod = BetterLinkMovementMethod.getInstance()
        Linkify.addLinks(holder.msgText!!, Linkify.WEB_URLS)
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, holder.msgText!!)
                .setOnLinkClickListener { textView, url ->
                    WebActivity.startActivity(url, context)
                    true
                }

        if (StringUtil.isNotNullOrEmpty(currentTheme.msgTime)) {
            holder.createdTime!!.text = StringUtil.getDateFromMillis(currentTheme.msgTime!!, Constants.TIME_PATTERN)
        }
        holder.theme = currentTheme
    }

    override fun getItemCount(): Int {
        return themes.size
    }

    inner class ViewHolder(internal var root: View) : RecyclerView.ViewHolder(root), View.OnClickListener {

        @BindView(R.id.forumName)
        lateinit var forumName: TextView
        @BindView(R.id.topicName)
        lateinit var topicName: TextView
        @BindView(R.id.msgText)
        lateinit var msgText: TextView
        @BindView(R.id.createdTime)
        lateinit var createdTime: TextView
        @BindView(R.id.userName)
        lateinit var userName: TextView

        internal var theme: Theme? = null

        init {
            ButterKnife.bind(this, root)
            root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            this@ThemesListingAdapter.themesListingView.onThemeClicked(theme!!)
        }
    }
}
