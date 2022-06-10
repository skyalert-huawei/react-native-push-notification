package com.dieam.reactnativepushnotification.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Location {
    public double coordinates[] = new double[2];
    public String type;

    public Location(JSONObject location) throws JSONException {
        JSONArray coordinates = location.getJSONArray("coordinates");
        this.coordinates[0] = coordinates.getDouble(0);
        this.coordinates[1] = coordinates.getDouble(1);
        this.type = location.getString("type");
    }
}
