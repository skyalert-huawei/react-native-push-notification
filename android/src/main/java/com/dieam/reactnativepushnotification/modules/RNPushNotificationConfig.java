package com.dieam.reactnativepushnotification.modules;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import androidx.core.content.res.ResourcesCompat;
import android.os.Bundle;
import android.util.Log;

class RNPushNotificationConfig {
    private static final String KEY_NOTIFICATION_FIREBASE_DEFAULT_CHANNEL_ID = "com.google.firebase.messaging.default_notification_channel_id";
    private static final String KEY_NOTIFICATION_DEFAULT_CHANNEL_ID = "com.dieam.reactnativepushnotification.default_notification_channel_id";
    private static final String KEY_NOTIFICATION_FOREGROUND = "com.dieam.reactnativepushnotification.notification_foreground";
    private static final String KEY_NOTIFICATION_COLOR = "com.dieam.reactnativepushnotification.notification_color";

    private static final String KEY_CHANNEL_NAME = "com.dieam.reactnativepushnotification.notification_channel_name";
    private static final String KEY_CHANNEL_DESCRIPTION = "com.dieam.reactnativepushnotification.notification_channel_description";
    private static final String KEY_CHANNEL_PREFIX = "com.dieam.reactnativepushnotification.channel_prefix";

    private static Bundle metadata;
    private Context context;

    public RNPushNotificationConfig(Context context) {
        this.context = context;
        if (metadata == null) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
                metadata = applicationInfo.metaData;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.e(RNPushNotification.LOG_TAG, "Error reading application meta, falling back to defaults");
                metadata = new Bundle();
            }
        }
    }

    private String getStringValue(String key, String defaultValue) {
        try {
            final String value = metadata.getString(key);

            if (value != null && value.length() > 0) {
                return value;
            }
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + key + " in manifest. Falling back to default");
        }

        // Default
        return defaultValue;
    }

    public int getNotificationColor() {
        try {
            int resourceId = metadata.getInt(KEY_NOTIFICATION_COLOR);
            return ResourcesCompat.getColor(context.getResources(), resourceId, null);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_NOTIFICATION_COLOR + " in manifest. Falling back to default");
        }
        // Default
        return -1;
    }

    public boolean getNotificationForeground() {
        try {
            return metadata.getBoolean(KEY_NOTIFICATION_FOREGROUND, false);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_NOTIFICATION_FOREGROUND + " in manifest. Falling back to default");
        }
        // Default
        return false;
    }

    public String getNotificationDefaultChannelId() {
        try {
            return getStringValue(KEY_NOTIFICATION_DEFAULT_CHANNEL_ID,
              getStringValue(KEY_NOTIFICATION_FIREBASE_DEFAULT_CHANNEL_ID, "fcm_fallback_notification_channel")
            );
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_NOTIFICATION_DEFAULT_CHANNEL_ID + " in manifest. Falling back to default");
        }
        // Default
        return "fcm_fallback_notification_channel";
    }

    public String getChannelNameForId(String channelId) {
        if (channelId != null) {
            try {
                return metadata.getString(KEY_CHANNEL_NAME + "." + channelId);
            } catch (Exception e) {
                Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_CHANNEL_NAME + "." + channelId + " in manifest. Falling back to default");
            }
        }
        // Default
        return "rn-push-notification-channel";
    }
    public String getChannelDescriptionForId(String channelId) {
        if (channelId != null) {
            try {
                return metadata.getString(KEY_CHANNEL_DESCRIPTION + "." + channelId);
            } catch (Exception e) {
                Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_CHANNEL_DESCRIPTION + "." + channelId + " in manifest. Falling back to default");
            }
        }
        // Default
        return "";
    }

    public String getChannelPrefix() {
        try {
            return metadata.getString(KEY_CHANNEL_PREFIX);
        } catch (Exception e) {
            Log.w(RNPushNotification.LOG_TAG, "Unable to find " + KEY_CHANNEL_PREFIX + " in manifest. Falling back to default");
        }
        // Default
        return "rn-push-notification-channel";
    }
}
