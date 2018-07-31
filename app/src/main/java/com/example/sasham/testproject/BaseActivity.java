package com.example.sasham.testproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.sasham.testproject.themes.ThemesListingFragment;
import com.example.sasham.testproject.util.NetworkUtil;
import com.example.sasham.testproject.util.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    private BroadcastReceiver internetBroadcastReceiver;


    private List<OnConnectionListener> onConnectionListeners=new ArrayList<>();

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

    public void addOnConnectionListener(OnConnectionListener onConnectionListener) {
        this.onConnectionListeners.add(onConnectionListener);
    }

    public void startListenInternetState() {
        if (onConnectionListeners.size()==0) {
            return;
        }
        callConnectionListeners(NetworkUtil.isConnectedNetwork(this));

        internetBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                callConnectionListeners(NetworkUtil.isConnectedNetwork(context));
            }
        };

        registerReceiver(internetBroadcastReceiver,
                new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void callConnectionListeners(boolean connectedNetwork) {
        for(OnConnectionListener onConnectionListener:onConnectionListeners){
            onConnectionListener.internetConnectionChanged(connectedNetwork);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (internetBroadcastReceiver != null) {
            unregisterReceiver(internetBroadcastReceiver);
        }
    }

    public void removeOnConnectionListener(OnConnectionListener onConnectionListener) {
        onConnectionListeners.remove(onConnectionListener);
    }

    public interface OnConnectionListener {
        void internetConnectionChanged(boolean connected);
    }
}
