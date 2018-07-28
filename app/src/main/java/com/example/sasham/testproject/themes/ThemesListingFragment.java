package com.example.sasham.testproject.themes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sasham.testproject.R;
import com.example.sasham.testproject.model.Theme;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesListingFragment extends Fragment implements ThemesListingView {

    private ThemesListingPresenter presenter = new ThemesListingPresenterImp();

    @BindView(R.id.themes_listing_recycler_view)
    public RecyclerView themesRecyclerView;

    @BindView(R.id.themes_progress)
    public ProgressBar themesProgress;

    @BindView(R.id.themes_empty_view)
    public TextView themesEmptyView;

    private ThemesListingAdapter themesListingAdapter;

    public ThemesListingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_themes_listing, container, false);

        //Init views
        ButterKnife.bind(this, root);

        themesListingAdapter = new ThemesListingAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        themesRecyclerView.setAdapter(themesListingAdapter);
        themesRecyclerView.setLayoutManager(layoutManager);

        //If we scrolled to end list then load more data
        themesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(!recyclerView.canScrollVertically(1)){
                    presenter.nextPage();
                }
            }
        });

        presenter.setView(this);
        presenter.firstPage();

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void showThemes(List<Theme> themeList) {
        themesProgress.setVisibility(View.GONE);
        themesListingAdapter.setThemes(themeList);
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
}
