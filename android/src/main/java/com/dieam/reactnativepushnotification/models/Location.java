package com.dieam.reactnativepushnotification.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Location {
    public JSONArray coordinates;
    public String type;

    public Location(JSONObject location) throws JSONException {
        this.coordinates = location.getJSONArray("coordinates");
        this.type = location.getString("type");
    }
}
