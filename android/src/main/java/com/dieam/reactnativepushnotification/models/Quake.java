package com.dieam.reactnativepushnotification.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Quake {
    public int intensity;
    public String startedAt;
    public String id;
    public Cluster cluster;

    public Quake(JSONObject quake) throws JSONException {
        this.intensity = quake.getInt("intensity");
        this.cluster = new Cluster(quake.getJSONObject("cluster"));
        this.startedAt = quake.getString("startedAt");
        this.id = quake.getString("id");
    }
}
