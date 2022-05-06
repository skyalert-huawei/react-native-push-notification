package com.dieam.reactnativepushnotification.models;

import org.json.JSONException;
import org.json.JSONObject;

public class Cluster {
    public Location location;
    public String name;
    public String state;

    public Cluster(JSONObject cluster) throws JSONException {
        this.name = cluster.getString("name");
        this.state = cluster.getString("state");
        this.location = new Location(cluster.getJSONObject("location"));
    }
}
