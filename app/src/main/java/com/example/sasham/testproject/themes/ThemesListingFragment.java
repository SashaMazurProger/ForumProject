package com.example.sasham.testproject.themes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sasham.testproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesListingFragment extends Fragment {

    @BindView(R.id.themes_listing_recycler_view)
    public RecyclerView themesRecyclerView;

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
        View root= inflater.inflate(R.layout.fragment_themes_listing, container, false);

        //Init views
        ButterKnife.bind(this,root);

        return root;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

}
