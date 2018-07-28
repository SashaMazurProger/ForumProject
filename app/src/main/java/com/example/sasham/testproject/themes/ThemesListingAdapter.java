package com.example.sasham.testproject.themes;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesListingAdapter extends RecyclerView.Adapter<ThemesListingAdapter.ViewHolder> {

    private List<Theme> themes = new ArrayList<>(20);

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.theme_listing_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Theme currentTheme=themes.get(position);

        holder.forumName.setText(currentTheme.getForumName());
        holder.topicName.setText(currentTheme.getTopicText());
        holder.msgText.setText(currentTheme.getMsgText());
        holder.createdTime.setText(currentTheme.getMsgTime());
    }

    @Override
    public int getItemCount() {
        return themes.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
