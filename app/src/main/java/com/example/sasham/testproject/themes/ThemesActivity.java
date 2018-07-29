package com.example.sasham.testproject.themes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.sasham.testproject.BaseActivity;
import com.example.sasham.testproject.R;
import com.example.sasham.testproject.messages.MessagesFragment;
import com.example.sasham.testproject.model.Theme;

public class ThemesActivity extends BaseActivity implements ThemesListingFragment.Callback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themes);

        showThemesFragment();
    }

    private void showThemesFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, new ThemesListingFragment())
                .commit();
    }

    @Override
    public void onThemeClicked(Theme theme) {
        Toast.makeText(this, theme.getForumName(), Toast.LENGTH_SHORT).show();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.themes_listing_container, MessagesFragment.newInstance(theme))
                .addToBackStack(null)
                .commit();
    }
}
