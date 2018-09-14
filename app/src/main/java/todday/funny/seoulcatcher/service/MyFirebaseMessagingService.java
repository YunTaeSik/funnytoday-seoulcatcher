package todday.funny.seoulcatcher.service;


import android.os.Handler;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import todday.funny.seoulcatcher.util.NotificationCreate;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private Handler handler = null;
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.d(TAG, ": " + remoteMessage.getFrom());
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (remoteMessage.getData().size() > 0) {
            NotificationCreate.startNotify(MyFirebaseMessagingService.this, remoteMessage.getData(), handler);
        }
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy");
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
