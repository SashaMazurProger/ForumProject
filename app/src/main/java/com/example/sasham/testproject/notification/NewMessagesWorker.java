package com.example.sasham.testproject.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.sasham.testproject.R;
import com.example.sasham.testproject.dependencies.WebestApiModule;
import com.example.sasham.testproject.model.FavoriteThemeInfo;
import com.example.sasham.testproject.model.FavoriteThemeInfoRepository;
import com.example.sasham.testproject.model.FavoriteThemeInfoRepositoryImp;
import com.example.sasham.testproject.model.Message;
import com.example.sasham.testproject.network.MessageAnswer;
import com.example.sasham.testproject.network.MessageWraper;
import com.example.sasham.testproject.network.WebestApi;
import com.example.sasham.testproject.themes.ThemesActivity;
import com.example.sasham.testproject.util.Converter;

import java.util.ArrayList;
import java.util.List;

import androidx.work.Worker;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NewMessagesWorker extends Worker implements Consumer<List<Message>> {

    private static final String NOTIF_CHANNEL_ID = "channel_id";
    private static final int NOTIFY_ID = 1;
    private static final String TAG = NewMessagesWorker.class.getSimpleName();
    private static final int PENDING_INTENT_REQUEST_CODE = 2;

    FavoriteThemeInfoRepository infoRepository = new FavoriteThemeInfoRepositoryImp();
    WebestApi webestApi = new WebestApiModule().webestApi();

    private boolean isNotifShowed = false;

    public NewMessagesWorker() {
    }

    @NonNull
    @Override
    public Result doWork() {
        checkFavoriteThemes();
        return Result.SUCCESS;
    }

    //Проверяем часто посещаемые форумы на наличие новых сообщений и показываем их в уведомлении
    private void checkFavoriteThemes() {

        for (final FavoriteThemeInfo themeInfo : infoRepository.getMostViewed(1)) {

            if (isNotifShowed) {
                return;
            }

            webestApi.themeMessages(themeInfo.getThemeId())
                    .map(new Function<MessageWraper, List<MessageAnswer>>() {
                        @Override
                        public List<MessageAnswer> apply(MessageWraper messageWraper) throws Exception {
                            return messageWraper.getMessageAnswers();
                        }
                    })
                    .flatMap(new Function<List<MessageAnswer>, ObservableSource<List<Message>>>() {
                        @Override
                        public ObservableSource<List<Message>> apply(List<MessageAnswer> messageAnswers) throws Exception {
                            List<Message> messages = new ArrayList<>();
                            for (MessageAnswer themeAnswer : messageAnswers) {
                                Message message = Converter.messageAnswerToMessage(themeAnswer);

                                if (isNewMessage(message, themeInfo.getLastMessageTime())) {
                                    messages.add(message);
                                }
                            }
                            return Observable.just(messages);
                        }
                    })
                    .subscribe(this,
                            new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {

                                }
                            });

        }
    }

    private boolean isNewMessage(Message message, long lastMessageTime) {
        long messageTime = Long.parseLong(message.getMsgTime());

        return messageTime > lastMessageTime;
    }


    private void showNotification(int size, String topicId) {
        Log.d(TAG, "showNotification: ---");

        Context context = getApplicationContext();
        NotificationCompat.Builder notificationBuilder;

        Intent intent = new Intent(context, ThemesActivity.class);
//        intent.putExtra(Constants.THEME_MODEL, topicId);
//        intent.setAction(ThemesActivity.SHOW_MESSAGES_ACTION);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                PENDING_INTENT_REQUEST_CODE,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(size);
        stringBuilder.append(" ");
        stringBuilder.append(getApplicationContext().getString(R.string.new_messages_notification));
        stringBuilder.append(" ");
        stringBuilder.append(topicId);

        notificationBuilder = new NotificationCompat.Builder(context, NOTIF_CHANNEL_ID)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText(stringBuilder.toString())
                .setSmallIcon(R.drawable.ic_message)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_people))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        if (notificationBuilder != null) {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
        }
    }

    @Override
    public void accept(List<Message> messages) throws Exception {
        Log.d(TAG, "accept: " + messages.size());

        //Если есть новые сообщения - выводим уведомление
        if (messages.size() > 0) {
            showNotification(messages.size(), messages.get(0).getTopicId());
            isNotifShowed = true;
        }
    }
}
