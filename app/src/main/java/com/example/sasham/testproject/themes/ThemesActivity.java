package com.example.sasham.testproject.themes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.BaseDaggerActivity;
import com.example.sasham.testproject.Constants;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.messages.MessagesActivity;
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp;
import com.example.sasham.testproject.model.Theme;
import com.example.sasham.testproject.util.NetworkUtil;
import com.example.sasham.testproject.util.PreferencesHelper;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemesActivity extends BaseDaggerActivity implements ThemesListingFragment.Callback, BaseActivity.OnConnectionListener {

    public static final String SHOW_MESSAGES_ACTION = "show_messages";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.themes_container)
    CoordinatorLayout mainContainer;

    private Snackbar snackbar;
    private boolean isConnected = false;
    private Fragment themesfragment;

    @Inject
    FavoriteThemeInfoRepositoryImp infoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_themes);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        setDefaultTitle();

        //Показываем фрагменты только если доступна сеть
        if (NetworkUtil.isConnectedNetwork(this)) {
            Intent intent = getIntent();

            if (intent.getAction().equals(SHOW_MESSAGES_ACTION)) {
                //TODO: show message fragment

            } else {
                showThemesFragment();
            }
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

        //Изменяем основную тему приложения
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

    //Показываем список топиков
    private void showThemesFragment() {
        themesfragment = new ThemesListingFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, themesfragment)
                .commit();
    }

    //Показываем сообщения топика, добавляя новый фрагмент в бэкстек
    @Override
    public void onThemeClicked(Theme theme) {
        infoRepository.addLastViewedTheme(theme);
        Intent intent = new Intent(this, MessagesActivity.class);
        intent.putExtra(Constants.THEME_MODEL, theme);
        startActivity(intent);
    }


    @Override
    public void internetConnectionChanged(boolean connected) {
        isConnected = connected;

        if (connected) {
            if (snackbar != null) {
                snackbar.dismiss();

                //Если не было сети и не был добавлен фрагмент - добавляем
                if (themesfragment == null) {
                    showThemesFragment();
                }
            }

        } else {
            snackbar = Snackbar.make(mainContainer, R.string.connection_failed, Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
        }
    }

}
