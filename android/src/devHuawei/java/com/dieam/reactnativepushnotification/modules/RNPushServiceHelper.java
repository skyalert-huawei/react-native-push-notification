package com.dieam.reactnativepushnotification.modules;

import static com.dieam.reactnativepushnotification.modules.RNPushNotification.LOG_TAG;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.huawei.agconnect.config.AGConnectServicesConfig;
import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.aaid.HmsInstanceId;
import com.huawei.hms.common.ApiException;
import com.huawei.hms.push.HmsMessaging;

public class RNPushServiceHelper {
    public static void getToken(Context context, RNPushNotificationListener listener) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // Obtain the app ID from the agconnect-services.json file.
                    String appId = AGConnectServicesConfig.fromContext(context).getString("client/app_id");

                    // Set tokenScope to HCM.
                    String tokenScope = "HCM";
                    String token = HmsInstanceId.getInstance(context).getToken(appId, tokenScope);
                    Log.i(LOG_TAG, "get token: " + token);

                    // Check whether the token is null.
                    if (!TextUtils.isEmpty(token)) {
                        listener.onSuccessGetToken(token);
                    }
                } catch (ApiException e) {
                    Log.e(LOG_TAG, "get token failed, " + e);
                }
            }
        }.start();
    }

    public static void deleteToken(Context context) {
        new Thread() {
            @Override
            public void run() {
                try {
                    // Obtain the app ID from the agconnect-services.json file.
                    String appId = AGConnectServicesConfig.fromContext(context).getString("client/app_id");

                    // Set tokenScope to HCM.
                    String tokenScope = "HCM";

                    // Delete the token.
                    HmsInstanceId.getInstance(context).deleteToken(appId, tokenScope);
                    Log.i(LOG_TAG, "token deleted successfully");
                } catch (ApiException e) {
                    Log.e(LOG_TAG, "deleteToken failed." + e);
                }
            }
        }.start();
    }

    public static void subscribeToTopic(Context context, String topic) {
        try {
            // Subscribe to a topic.
            HmsMessaging.getInstance(context).subscribe(topic)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            // Obtain the topic subscription result.
                            if (task.isSuccessful()) {
                                Log.i(LOG_TAG, "subscribe topic successfully");
                            } else {
                                Log.e(LOG_TAG, "subscribe topic failed, return value is " + task.getException().getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e(LOG_TAG, "subscribe failed, catch exception : " + e.getMessage());
        }
    }

    public static void unsubscribeFromTopic(Context context, String topic) {
        try {
            // Unsubscribe from a topic.
            HmsMessaging.getInstance(context).unsubscribe(topic)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(Task<Void> task) {
                            // Obtain the topic unsubscription result.
                            if (task.isSuccessful()) {
                                Log.i(LOG_TAG, "unsubscribe topic successfully");
                            } else {
                                Log.e(LOG_TAG, "unsubscribe topic failed, return value is " + task.getException().getMessage());
                            }
                        }
                    });
        } catch (Exception e) {
            Log.e(LOG_TAG, "unsubscribe failed, catch exception : " + e.getMessage());
        }
    }
}
