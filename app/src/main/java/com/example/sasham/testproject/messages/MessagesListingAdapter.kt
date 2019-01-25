package com.example.sasham.testproject.messages

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class MessagesListingAdapter(private val messages: List<Message>) : androidx.recyclerview.widget.RecyclerView.Adapter<MessagesListingAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val root = LayoutInflater.from(parent.context).inflate(R.layout.message_listing_item, parent, false)
        return ViewHolder(root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val context = holder.itemView.context
        val currentMessage = messages[position]

        holder.userName!!.text = currentMessage.userName

        holder.msgCount!!.text = if (StringUtil.isNotNullOrEmpty(currentMessage.msgCount))
            currentMessage.msgCount
        else
            "0"

        holder.messageText!!.text = StringUtil.stripHtml(currentMessage.msgText)
        holder.messageText!!.movementMethod = BetterLinkMovementMethod.getInstance()
        Linkify.addLinks(holder.messageText!!, Linkify.WEB_URLS)
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, holder.messageText!!)
                .setOnLinkClickListener { textView, url ->
                    WebActivity.startActivity(url, context)
                    true
                }

        if (StringUtil.isNotNullOrEmpty(currentMessage.msgTime)) {
            holder.createdTime!!.text = StringUtil.getDateFromMillis(currentMessage.msgTime!!, Constants.TIME_PATTERN)
        }

        if (StringUtil.isNotNullOrEmpty(currentMessage.avatar)) {
            Picasso.get()
                    .load(StringUtil.getAvatarUrl(currentMessage.avatar!!))
                    .placeholder(R.mipmap.ic_avatar)
                    .into(holder.avatar)
        } else {
            holder.avatar!!.setImageResource(R.mipmap.ic_avatar)
        }
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    inner class ViewHolder(val root: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(root) {


        var userName: TextView = root.findViewById(R.id.userName)

        var messageText: TextView = root.findViewById(R.id.msgText)

        var createdTime: TextView = root.findViewById(R.id.createdTime)

        var msgCount: TextView = root.findViewById(R.id.messagesCount)

        var avatar: CircleImageView = root.findViewById(R.id.avatar)

    }
}
