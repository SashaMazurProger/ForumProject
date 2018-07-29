package com.example.sasham.testproject.messages;

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

import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        Message currentMessage = messages.get(position);

        holder.userName.setText(currentMessage.getUserName());

        holder.messageText.setText(currentMessage.getMsgText());
        holder.messageText.setMovementMethod(BetterLinkMovementMethod.getInstance());
        Linkify.addLinks(holder.messageText,Linkify.WEB_URLS);
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS,holder.messageText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        Toast.makeText(holder.itemView.getContext(), "Oops", Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });


        holder.createdTime.setText(currentMessage.getMsgTime());
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
