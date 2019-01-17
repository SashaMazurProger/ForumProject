package com.example.sasham.testproject

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v4.app.NotificationCompat
import android.util.Log

import com.example.sasham.testproject.notification.NewMessagesWorker
import com.example.sasham.testproject.themes.ThemesActivity

import java.io.IOException
import java.util.concurrent.TimeUnit

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager

import android.content.Context.NOTIFICATION_SERVICE

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intentArg: Intent) {


        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val newMessagesWork = PeriodicWorkRequest.Builder(NewMessagesWorker::class.java, 1, TimeUnit.HOURS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance()
                .enqueue(newMessagesWork)
    }

    companion object {

        private val TAG = BootCompletedReceiver::class.java.simpleName
    }
}
