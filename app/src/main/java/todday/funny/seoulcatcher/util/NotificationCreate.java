package todday.funny.seoulcatcher.util;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.RemoteViews;

import com.bumptech.glide.request.target.NotificationTarget;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

import todday.funny.seoulcatcher.GlideApp;
import todday.funny.seoulcatcher.R;
import todday.funny.seoulcatcher.model.Call;
import todday.funny.seoulcatcher.ui.activity.IntroActivity;

public class NotificationCreate {
    private final static int NOTIFICATION_ID = 111;

    public static void startNotify(final Context context, Map<String, String> data, Handler handler) {
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(data);
        final Call call = gson.fromJson(jsonElement, Call.class);
        Intent intent = new Intent(context, IntroActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification_layout);
        remoteViews.setTextViewText(R.id.text_time, DateFormat.getCallTime(System.currentTimeMillis()));


        String name = context.getString(R.string.call_user_name, call.getToUserName()) + " / " + call.getKind();
        if (call.getKind().equals(context.getString(R.string.cardiac_arrest))) {
            name += " / " + call.getAge();
        }
        remoteViews.setTextViewText(R.id.text_to_user_name, name);

        String location = context.getString(R.string.location) + " : " + call.getAddress();
        remoteViews.setTextViewText(R.id.text_location, location);

        Notification notification = new NotificationCompat.Builder(context, context.getString(R.string.notification_channel_id))
                .setSmallIcon(R.drawable.ic_bell_64px)
                .setContentIntent(pendingIntent)
                .setCustomContentView(remoteViews)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_STATUS)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(NOTIFICATION_ID, notification);

        final NotificationTarget userTarget = new NotificationTarget(context, R.id.image_touser, remoteViews, notification, NOTIFICATION_ID);
        handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                GlideApp.with(context).asBitmap().circleCrop().load(call.getToUserPhotoUrl()).into(userTarget);

            }
        });
    }

    public static void stopNotify(Context context) {
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
