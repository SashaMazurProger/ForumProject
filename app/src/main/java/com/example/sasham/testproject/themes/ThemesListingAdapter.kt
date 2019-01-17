package com.example.sasham.testproject.themes

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Theme
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class ThemesListingAdapter(private val themes: MutableList<Theme>, private val themesListingView: ThemesListingView) : androidx.recyclerview.widget.RecyclerView.Adapter<ThemesListingAdapter.ViewHolder>() {

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

    inner class ViewHolder(internal var root: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(root), View.OnClickListener {


        var forumName: TextView = root.findViewById(R.id.forumName)

        var topicName: TextView = root.findViewById(R.id.topicName)

        var msgText: TextView = root.findViewById(R.id.msgText)

        var createdTime: TextView = root.findViewById(R.id.createdTime)

        var userName: TextView = root.findViewById(R.id.userName)

        internal var theme: Theme? = null

        init {
            root.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            this@ThemesListingAdapter.themesListingView.onThemeClicked(theme!!)
        }
    }
}
