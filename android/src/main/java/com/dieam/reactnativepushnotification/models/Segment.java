package com.dieam.reactnativepushnotification.models;


import org.json.JSONException;
import org.json.JSONObject;

public class Segment {
    public String id;
    public String tag;


    public Segment(JSONObject segment) throws JSONException {
        this.id = segment.getString("id");
        this.tag = segment.getString("tag");
    }

}
