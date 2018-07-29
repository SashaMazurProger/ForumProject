package com.example.sasham.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sasham.testproject.util.PreferencesHelper;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        updateTheme();
    }

    public void updateTheme() {
        if (PreferencesHelper.getTheme(getApplicationContext()) == Constants.THEME_BLACK) {
            setTheme(R.style.AppTheme_Black);
        } else {
            setTheme(R.style.AppTheme_White);
        }
    }

    public void recreateActivity() {
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }
}
