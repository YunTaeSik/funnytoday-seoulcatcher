package todday.funny.seoulcatcher.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.Map;

import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.ui.activity.IntroActivity;
import todday.funny.seoulcatcher.ui.activity.MainActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();
    private final static int NOTIFICATION_ID = 111;


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, ": " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            sendNotification(remoteMessage.getData());
        }
    }

    private void sendNotification(Map<String, String> data) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(data);
        Call call = gson.fromJson(jsonElement, Call.class);
        Intent intent = new Intent(this, IntroActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_layout);

        Notification notification = new NotificationCompat.Builder(this, getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_bell_64px)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_STATUS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

}
