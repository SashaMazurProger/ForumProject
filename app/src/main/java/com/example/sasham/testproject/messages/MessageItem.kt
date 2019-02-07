package com.example.sasham.testproject.messages

import android.text.util.Linkify
import com.example.sasham.testproject.Constants
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.util.StringUtil
import com.example.sasham.testproject.website.WebActivity
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.message_item.view.*
import me.saket.bettermovementmethod.BetterLinkMovementMethod

class MessageItem(val message: Message) : Item() {
    override fun getLayout(): Int {
        return R.layout.message_item
    }

    override fun bind(holder: ViewHolder, position: Int) {

        val view = holder.itemView

        view.topicName!!.text = message.topicName

        view.messagesCount!!.text = if (StringUtil.isNotNullOrEmpty(message.msgCount))
            message.msgCount
        else
            "0"

        view.messageText!!.text = StringUtil.stripHtml(message.msgText)
        view.messageText!!.movementMethod = BetterLinkMovementMethod.getInstance()
        Linkify.addLinks(view.messageText!!, Linkify.WEB_URLS)
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, view.messageText!!)
                .setOnLinkClickListener { textView, url ->
                    WebActivity.startActivity(url, view.context)
                    true
                }

        if (StringUtil.isNotNullOrEmpty(message.msgTime)) {
            view.messageCreated!!.text = StringUtil.getDateFromMillis(message.msgTime!!, Constants.TIME_PATTERN)
        }

        if (StringUtil.isNotNullOrEmpty(message.avatar)) {
            Picasso.get()
                    .load(StringUtil.getAvatarUrl(message.avatar!!))
                    .placeholder(R.mipmap.ic_avatar)
                    .into(view.avatar)
        } else {
            view.avatar!!.setImageResource(R.mipmap.ic_avatar)
        }
    }
}