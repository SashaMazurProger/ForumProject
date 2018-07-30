package com.example.sasham.testproject.themes;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.BaseDaggerActivity;
import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.messages.MessagesFragment;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.util.NetworkUtil;
import com.example.sasham.testproject.util.PreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesActivity extends BaseDaggerActivity implements ThemesListingFragment.Callback, BaseActivity.OnConnectionListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.themes_container)
    CoordinatorLayout mainContainer;

    private Snackbar snackbar;
    private boolean isConnected=false;

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
                if (fragment != null && fragment instanceof MessagesFragment == false) {
                    setDefaultTitle();
                }
            }
        });

        if (NetworkUtil.isConnectedNetwork(this)) {
            showThemesFragment();
        }

        addOnConnectionListener(this);
        startListenInternetState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.themes_menu, menu);

        MenuItem theme = menu.findItem(R.id.action_black_theme);
        theme.setChecked(PreferencesHelper.getTheme(getApplicationContext()) == Constants.THEME_BLACK);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_black_theme) {

            boolean isChecked = !item.isChecked();
            item.setChecked(isChecked);

            if (isChecked) {
                PreferencesHelper.setTheme(getApplicationContext(), Constants.THEME_BLACK);
            } else {
                PreferencesHelper.setTheme(getApplicationContext(), Constants.THEME_WHITE);
            }
            updateTheme();
            recreateActivity();

        }

        return super.onOptionsItemSelected(item);
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
       if(isConnected){
           setTitle(theme.getForumName());
           getSupportFragmentManager()
                   .beginTransaction()
                   .replace(R.id.themes_listing_container, MessagesFragment.newInstance(theme))
                   .addToBackStack(null)
                   .commit();
       }
    }

    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected=connected;

        if (connected) {
            if (snackbar != null) {
                snackbar.dismiss();

                Fragment fragment=getSupportFragmentManager().findFragmentById(R.id.themes_listing_container);
                if( fragment==null){
                    showThemesFragment();
                }
            }

        } else {
            snackbar = Snackbar.make(mainContainer, R.string.connection_failed, Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }
}
