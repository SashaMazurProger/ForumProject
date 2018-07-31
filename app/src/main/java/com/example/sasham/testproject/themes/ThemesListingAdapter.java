package com.example.sasham.testproject.themes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.util.StringUtil;
import com.example.sasham.testproject.website.WebActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class ThemesListingAdapter extends RecyclerView.Adapter<ThemesListingAdapter.ViewHolder> {

    private List<Theme> themes;
    private ThemesListingView themesListingView;

    public ThemesListingAdapter(List<Theme> themes, ThemesListingView themesListingView) {
        this.themes = themes;
        this.themesListingView = themesListingView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_listing_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        Theme currentTheme = themes.get(position);

        holder.userName.setText(currentTheme.getUserName());
        holder.forumName.setText(currentTheme.getForumName());
        holder.topicName.setText(currentTheme.getTopicText());

        holder.msgText.setText(StringUtil.stripHtml(currentTheme.getMsgText()));
        holder.msgText.setMovementMethod(BetterLinkMovementMethod.getInstance());
        Linkify.addLinks(holder.msgText, Linkify.WEB_URLS);
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, holder.msgText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        WebActivity.startActivity(url, context);
                        return true;
                    }
                });

        if (StringUtil.isNotNullOrEmpty(currentTheme.getMsgTime())) {
            holder.createdTime.setText(StringUtil.getDateFromMillis(currentTheme.getMsgTime(), Constants.TIME_PATTERN));
        }
        holder.theme = currentTheme;
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        View root;

        @BindView(R.id.theme_forum_name)
        TextView forumName;
        @BindView(R.id.theme_topic_text)
        TextView topicName;
        @BindView(R.id.theme_msg_text)
        TextView msgText;
        @BindView(R.id.theme_created)
        TextView createdTime;
        @BindView(R.id.theme_user_name)
        TextView userName;

        Theme theme;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            root = itemView;
            root.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            ThemesListingAdapter.this.themesListingView.onThemeClicked(theme);
        }
    }
}
