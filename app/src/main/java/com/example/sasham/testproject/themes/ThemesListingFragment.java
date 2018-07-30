package com.example.sasham.testproject.themes;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class ThemesListingFragment extends Fragment implements ThemesListingView, BaseActivity.OnConnectionListener {

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

    private ThemesListingAdapter themesListingAdapter;
    private Unbinder unbinder;
    private BaseActivity baseActivity;
    private boolean isConnected = false;


    public ThemesListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
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
                    if (isConnected) {
                        presenter.nextPage();
                    }
                }
            }
        });

        return root;
    }

    private boolean mayLoadMoreData() {
        return !themesRecyclerView.canScrollVertically(1);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);

        if(baseActivity!=null){
            baseActivity.addOnConnectionListener(this);
        }

        if (savedInstanceState != null) {
            themes = savedInstanceState.getParcelableArrayList(Constants.THEME_MODEL);
            themesListingAdapter.notifyDataSetChanged();
        } else {
            presenter.firstPage();
        }
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);

        if (context instanceof Callback) {
            callback = (Callback) context;
        }
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.THEME_MODEL, (ArrayList<? extends Parcelable>) themes);
    }

    @Override
    public void showThemes(List<Theme> themeList) {

        themesProgress.setVisibility(View.GONE);

        if (themeList.size() == 0) {
            themesEmptyView.setVisibility(View.VISIBLE);
            themesEmptyView.setText(getString(R.string.no_themes));
        } else {
            themesEmptyView.setVisibility(View.GONE);
        }

        themes.clear();
        themes.addAll(themeList);
        themesListingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {
        themesEmptyView.setVisibility(View.GONE);
        themesProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void onError(String errorMessage) {
        themesRecyclerView.setVisibility(View.GONE);
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
        baseActivity.removeOnConnectionListener(this);
        presenter.destroy();
        unbinder.unbind();
    }

    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected = connected;
        if(isConnected){
            if(mayLoadMoreData()){
                presenter.nextPage();
            }
        }
    }

    public interface Callback {
        void onThemeClicked(Theme theme);
    }
}
