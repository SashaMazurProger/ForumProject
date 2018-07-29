package com.example.sasham.testproject.themes;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.messages.MessagesFragment;
import com.example.sasham.testproject.model.Theme;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesActivity extends BaseActivity implements ThemesListingFragment.Callback {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setDefaultTitle();

        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                Fragment fragment = getSupportFragmentManager()
                        .findFragmentById(R.id.themes_listing_container);
                if(fragment!=null && fragment instanceof MessagesFragment == false){
                    setDefaultTitle();
                }
            }
        });

        showThemesFragment();
    }

    private void setDefaultTitle() {
        setTitle(getString(R.string.app_name));
    }

    private void showThemesFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, new ThemesListingFragment())
                .commit();
    }

    @Override
    public void onThemeClicked(Theme theme) {
        setTitle(theme.getForumName());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, MessagesFragment.newInstance(theme))
                .addToBackStack(null)
                .commit();
    }

}
