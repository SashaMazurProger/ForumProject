package com.example.sasham.testproject

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat

import com.example.sasham.testproject.notification.NewMessagesWorker

import java.util.concurrent.TimeUnit

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager

class BootCompletedReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intentArg: Intent) {


        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val newMessagesWork = PeriodicWorkRequest.Builder(NewMessagesWorker::class.java, 30, TimeUnit.SECONDS)
                .setConstraints(constraints)
                .build()

        WorkManager.getInstance()
                .enqueue(newMessagesWork)
    }

    companion object {

        private val TAG = BootCompletedReceiver::class.java.simpleName
    }
}
