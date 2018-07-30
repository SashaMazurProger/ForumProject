package com.example.sasham.testproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sasham.testproject.notification.NewMessagesWorker;
import com.example.sasham.testproject.themes.ThemesActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import androidx.work.Constraints;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import static android.content.Context.NOTIFICATION_SERVICE;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final String TAG = BootCompletedReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intentArg) {

        Log.d(TAG, "doInBackground: notified");

        OneTimeWorkRequest newMessagesRequest = new OneTimeWorkRequest
                .Builder(NewMessagesWorker.class)
                .build();

        Constraints constraints=new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build();

        PeriodicWorkRequest newMessagesWork = new PeriodicWorkRequest
                .Builder(NewMessagesWorker.class,20, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build();

        WorkManager.getInstance()
                .enqueue(newMessagesRequest);
    }
}
