package com.example.sasham.testproject.messages;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.util.StringUtil;
import com.example.sasham.testproject.website.WebActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment implements MessagesListingView, SwipeRefreshLayout.OnRefreshListener, BaseActivity.OnConnectionListener {

    @Inject
    public MessagesListingPresenter presenter;

    private List<Message> messages = new ArrayList<>();
    private Theme theme;

    @BindView(R.id.messages_recycler_view)
    RecyclerView messagesRecyclerView;

    @BindView(R.id.messages_progress)
    ProgressBar messageProgress;

    @BindView(R.id.messages_empty_view)
    TextView emptyView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;


    //Theme
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


    private Unbinder unbinder;
    private MessagesListingAdapter messagesListingAdapter;
    private BaseActivity baseActivity;
    private boolean isConnected = false;
    private boolean isDataLoaded = false;

    public MessagesFragment() {
        // Required empty public constructor
    }

    public static MessagesFragment newInstance(Theme theme) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.THEME_MODEL, theme);
        MessagesFragment messagesFragment = new MessagesFragment();
        messagesFragment.setArguments(bundle);
        return messagesFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_messages, container, false);
        unbinder = ButterKnife.bind(this, root);

        messagesListingAdapter = new MessagesListingAdapter(messages);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(container.getContext(), RecyclerView.VERTICAL, false);
        messagesRecyclerView.setAdapter(messagesListingAdapter);
        messagesRecyclerView.setLayoutManager(layoutManager);

        swipeRefreshLayout.setOnRefreshListener(this);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        theme = getArguments().getParcelable(Constants.THEME_MODEL);

        presenter.setView(this);

        if (baseActivity != null) {
            baseActivity.addOnConnectionListener(this);
        }

        if (theme != null) {
            setThemeReview();
            presenter.fetchMessages(theme.getId());
        } else {
            onError(getString(R.string.no_messages));
        }
    }

    private void setThemeReview() {
        Theme currentTheme = theme;

        userName.setText(currentTheme.getUserName());
        forumName.setText(currentTheme.getForumName());
        topicName.setText(currentTheme.getTopicText());

        msgText.setText(StringUtil.stripHtml(currentTheme.getMsgText()));
        msgText.setMovementMethod(BetterLinkMovementMethod.getInstance());
        Linkify.addLinks(msgText, Linkify.WEB_URLS);
        BetterLinkMovementMethod.linkify(Linkify.WEB_URLS, msgText)
                .setOnLinkClickListener(new BetterLinkMovementMethod.OnLinkClickListener() {
                    @Override
                    public boolean onClick(TextView textView, String url) {
                        WebActivity.startActivity(url, getContext());
                        return true;
                    }
                });

        if (StringUtil.isNotNullOrEmpty(currentTheme.getMsgTime())) {
            createdTime.setText(StringUtil.getDateFromMillis(currentTheme.getMsgTime(), Constants.TIME_PATTERN));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.destroy();
        if (baseActivity != null) {
            baseActivity.removeOnConnectionListener(this);
        }
    }

    @Override
    public void onDetach() {
        baseActivity = null;
        super.onDetach();
    }

    @Override
    public void showThemeMessages(List<Message> messageList) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        messageProgress.setVisibility(View.GONE);

        if (messageList.size() == 0) {
            onError(getString(R.string.no_messages));
        } else {
            emptyView.setVisibility(View.GONE);
        }

        isDataLoaded = true;
        messages.clear();
        messages.addAll(messageList);
        messagesListingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {
        emptyView.setVisibility(View.GONE);

        if (!swipeRefreshLayout.isRefreshing()) {
            messageProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String message) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        messagesRecyclerView.setVisibility(View.GONE);
        messageProgress.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(message);

        isDataLoaded = false;
    }

    @Override
    public void onRefresh() {
        if (isConnected) {
            swipeRefreshLayout.setRefreshing(true);
            presenter.loadNewData(theme.getId());
        } else if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected = connected;
        if (mayDownloadData()) {
            presenter.fetchMessages(theme.getId());
        }
    }

    private boolean mayDownloadData() {
        return isConnected && !isDataLoaded;
    }
}
