package com.example.sasham.testproject.themes;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;

public class ThemesListingFragment extends Fragment implements ThemesListingView, BaseActivity.OnConnectionListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    public ThemesListingPresenter presenter;

    private Callback callback;
    private List<Theme> themes = new ArrayList<>();

    @BindView(R.id.themes_listing_recycler_view)
    public RecyclerView themesRecyclerView;

    @BindView(R.id.themes_progress)
    public ProgressBar themesProgress;

    @BindView(R.id.themes_empty_view)
    public TextView themesEmptyView;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private ThemesListingAdapter themesListingAdapter;
    private Unbinder unbinder;
    private BaseActivity baseActivity;
    private boolean isConnected = false;


    public ThemesListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callback) {
            callback = (Callback) context;
        }
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        AndroidSupportInjection.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_themes_listing, container, false);

        //Init views
        unbinder = ButterKnife.bind(this, root);

        themesListingAdapter = new ThemesListingAdapter(themes, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        themesRecyclerView.setAdapter(themesListingAdapter);
        themesRecyclerView.setLayoutManager(layoutManager);

        //If we scrolled to end list then load more data
        themesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (mayLoadMoreData()) {
                    presenter.nextPage();
                }
            }
        });

        swipeRefreshLayout.setOnRefreshListener(this);

        return root;
    }

    private boolean mayLoadMoreData() {
        return !themesRecyclerView.canScrollVertically(1) && isConnected;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.app_name));

        presenter.setView(this);

        if (baseActivity != null) {
            baseActivity.addOnConnectionListener(this);
        }

        //В случае если фрагмент не загрузил данные - загружаем первую страницу.
        // Пропускаем если данные уже есть и нам не нужно их заменять
        if (themes.size() == 0) {
            presenter.firstPage();
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.THEME_MODEL, (ArrayList<? extends Parcelable>) themes);
    }

    @Override
    public void showThemes(List<Theme> themeList) {

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
        themesProgress.setVisibility(View.GONE);

        if (themeList.size() == 0) {
            onError(getString(R.string.no_themes));
        }

        themes.clear();
        themes.addAll(themeList);
        themesListingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {
        themesEmptyView.setVisibility(View.GONE);

        if (!swipeRefreshLayout.isRefreshing()) {
            themesProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onError(String errorMessage) {
//        themesRecyclerView.setVisibility(View.GONE);
        themesProgress.setVisibility(View.GONE);
        themesEmptyView.setText(errorMessage);
    }

    @Override
    public void onThemeClicked(Theme theme) {
        if (callback != null) {
            callback.onThemeClicked(theme);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (baseActivity != null) {
            baseActivity.removeOnConnectionListener(this);
        }
        presenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected = connected;
        if (mayLoadMoreData()) {
            presenter.nextPage();
        }
    }

    @Override
    public void onRefresh() {
        if (isConnected) {
            swipeRefreshLayout.setRefreshing(true);
            presenter.loadNewData();
        } else {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onDetach() {
        callback = null;
        baseActivity = null;
        super.onDetach();
    }

    public interface Callback {
        void onThemeClicked(Theme theme);
    }
}
