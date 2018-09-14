package todday.funny.seoulcatcher;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.messaging.FirebaseMessaging;

import todday.funny.seoulcatcher.util.Keys;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic(Keys.TOPIC_KEY);
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
