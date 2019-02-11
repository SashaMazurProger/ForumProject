package com.example.sasham.testproject.notification

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import com.example.sasham.testproject.App
import com.example.sasham.testproject.R
import com.example.sasham.testproject.model.DataRepository
import com.example.sasham.testproject.model.Message
import com.example.sasham.testproject.themes.ThemesActivity
import io.reactivex.Observable
import java.util.*
import javax.inject.Inject

class NewMessagesWorker : Worker() {

    @Inject
    lateinit var data: DataRepository

    private var isNotifShowed = false

    init {
        App.instance!!.appComp!!.inject(this)
    }

    override fun doWork(): Worker.Result {
        checkFavoriteThemes()
        return Worker.Result.SUCCESS
    }

    //Проверяем часто избранные форумы на наличие новых сообщений и показываем их кв уведомлении
    private fun checkFavoriteThemes() {

//
//        if (isNotifShowed) {
//            return
//        }

        data.favoriteThemes()
                .flatMap { Observable.fromIterable(it) }
                .flatMap {
                    data.themeMessages(it.themeId.toString())
                            .flatMap { messages ->
                                val newMessages = ArrayList<Message>()
                                for (message in messages) {
                                    if (it.lastTimeViewedInMillis != null && isNewMessage(message, it.lastTimeViewedInMillis!!)) {
                                        newMessages.add(message)
                                    }
                                }
                                Observable.just(newMessages)
                            }
                            .map { messages: List<Message> ->
                                Log.d(TAG, "accept: " + messages.size)

                                //Если есть новые сообщения - выводим уведомление
//                                if (messages.isNotEmpty()) {
                                showNotification(messages.size, it.topicText)
                                isNotifShowed = true
//                                }
                            }
                }
                .blockingSubscribe({
                },
                        {

                        })


    }

    private fun isNewMessage(message: Message, lastViewedTime: Long): Boolean {
        val messageTime = java.lang.Long.parseLong(message.msgTime) * 1000

        return messageTime > lastViewedTime
    }


    private fun showNotification(size: Int, topicId: String?) {
        Log.d(TAG, "showNotification: ---")

        val context = applicationContext
        val notificationBuilder: NotificationCompat.Builder?

        val intent = Intent(context, ThemesActivity::class.java)

        val pendingIntent = PendingIntent.getActivity(context,
                PENDING_INTENT_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)

        val stringBuilder = StringBuilder()
        stringBuilder.append(size)
        stringBuilder.append(" ")
        stringBuilder.append(applicationContext.getString(R.string.new_messages_notification))
        stringBuilder.append(" ")
        stringBuilder.append(topicId)

        notificationBuilder = NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(stringBuilder.toString())
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.ic_people))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)


        if (notificationBuilder != null) {
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(NOTIFY_ID, notificationBuilder.build())
        }
    }

    companion object {

        private val NOTIF_CHANNEL_ID = "channel_id"
        private val NOTIFY_ID = 1
        private val TAG = NewMessagesWorker::class.java.simpleName
        private val PENDING_INTENT_REQUEST_CODE = 2
    }


}
