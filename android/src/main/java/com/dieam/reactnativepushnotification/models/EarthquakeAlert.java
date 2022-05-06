package com.dieam.reactnativepushnotification.models;

import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class EarthquakeAlert {
    public boolean isTestSystem;
    public Local local;
    public Quake quake;

    public EarthquakeAlert(Bundle data) {
        try {
            this.isTestSystem = data.getBoolean("isTestSystem");
            this.local = new Local(new JSONObject(data.getString("local")));
            this.quake = new Quake(new JSONObject(data.getString("quake")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
