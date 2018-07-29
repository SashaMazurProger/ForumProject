package com.example.sasham.testproject.themes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ThemesListingFragment extends Fragment implements ThemesListingView {

    private ThemesListingPresenter presenter = new ThemesListingPresenterImp();
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

                if (!recyclerView.canScrollVertically(1)) {
                    presenter.nextPage();
                }
            }
        });

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.setView(this);

        if (savedInstanceState != null) {
            themes = savedInstanceState.getParcelableArrayList(Constants.THEME);
            themesListingAdapter.notifyDataSetChanged();
        } else {
            presenter.firstPage();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Callback) {
            callback = (Callback) context;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(Constants.THEME, (ArrayList<? extends Parcelable>) themes);
    }

    @Override
    public void showThemes(List<Theme> themeList) {

        themesProgress.setVisibility(View.GONE);

        themes.clear();
        themes.addAll(themeList);
        themesListingAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoading() {

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
        presenter.destroy();
        unbinder.unbind();
    }

    public interface Callback {
        void onThemeClicked(Theme theme);
    }
}
