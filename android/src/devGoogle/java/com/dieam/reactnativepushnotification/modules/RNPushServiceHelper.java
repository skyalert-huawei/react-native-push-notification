package com.dieam.reactnativepushnotification.modules;

import static com.dieam.reactnativepushnotification.modules.RNPushNotification.LOG_TAG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

public class RNPushServiceHelper {
    public static void getToken(Context context, RNPushNotificationListener listener) {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.e(LOG_TAG, "exception", task.getException());
                            return;
                        }
                        listener.onSuccessGetToken(task.getResult());
                    }
                });
    }

    public static void deleteToken(Context context) {
        FirebaseMessaging.getInstance().deleteToken();
    }

    public static void subscribeToTopic(Context context, String topic) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic);
    }

    public static void unsubscribeFromTopic(Context context, String topic) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
    }
}
