package com.dieam.reactnativepushnotification.models;

import org.json.JSONException;
import org.json.JSONObject;

public class NotificationData {
    public String title;
    public String message;
    public String soundName;

    public NotificationData(JSONObject notification) throws JSONException {
        this.title = notification.getString("title");
        this.message = notification.getString("message");
    }
}
