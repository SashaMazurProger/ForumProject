package com.example.sasham.testproject.messages;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.util.StringUtil;
import com.example.sasham.testproject.website.WebActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class MessagesListingAdapter extends RecyclerView.Adapter<MessagesListingAdapter.ViewHolder> {

    private List<Message> messages;

    public MessagesListingAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_listing_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Context context = holder.itemView.getContext();
        Message currentMessage = messages.get(position);

        holder.userName.setText(currentMessage.getUserName());

        holder.msgCount.setText(
                StringUtil.isNotNullOrEmpty(currentMessage.getMsgCount())?
                        currentMessage.getMsgCount() : "0");

        holder.messageText.setText(StringUtil.stripHtml(currentMessage.getMsgText()));
        holder.messageText.setMovementMethod(BetterLinkMovementMethod.getInstance());
        Linkify.addLinks(holder.messageText, Linkify.WEB_URLS);
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, holder.messageText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        WebActivity.startActivity(url, context);
                        return true;
                    }
                });

        if (StringUtil.isNotNullOrEmpty(currentMessage.getMsgTime())) {
            holder.createdTime.setText(StringUtil.getDateFromMillis(currentMessage.getMsgTime(), Constants.TIME_PATTERN));
        }

        if (StringUtil.isNotNullOrEmpty(currentMessage.getAvatar())) {
            Picasso.get()
                    .load(StringUtil.getAvatarUrl(currentMessage.getAvatar()))
                    .placeholder(R.mipmap.ic_avatar)
                    .into(holder.avatar);
        } else {
            holder.avatar.setImageResource(R.mipmap.ic_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message_user_name)
        TextView userName;
        @BindView(R.id.message_text)
        TextView messageText;
        @BindView(R.id.message_created)
        TextView createdTime;
        @BindView(R.id.message_msg_count)
        TextView msgCount;
        @BindView(R.id.message_avatar)
        CircleImageView avatar;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
