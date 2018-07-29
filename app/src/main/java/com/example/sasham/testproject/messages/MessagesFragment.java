package com.example.sasham.testproject.messages;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessagesFragment extends Fragment implements MessagesListingView {

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

    private Unbinder unbinder;
    private MessagesListingAdapter messagesListingAdapter;

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
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
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

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        theme = getArguments().getParcelable(Constants.THEME_MODEL);

        presenter.setView(this);
        if (theme != null) {
            presenter.fetchMessages(theme.getId());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showThemeMessages(List<Message> messageList) {
        messageProgress.setVisibility(View.GONE);

        if (messageList.size() == 0) {
            emptyView.setVisibility(View.VISIBLE);
            emptyView.setText(getString(R.string.no_messages));
        }
        else {
            emptyView.setVisibility(View.GONE);
        }
        messages.clear();
        messages.addAll(messageList);
        messagesListingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {
        messageProgress.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
    }

    @Override
    public void onError(String message) {
        messageProgress.setVisibility(View.GONE);
        messageProgress.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        emptyView.setText(message);
    }

}
