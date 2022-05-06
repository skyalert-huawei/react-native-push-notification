package com.dieam.reactnativepushnotification.models;


import org.json.JSONException;
import org.json.JSONObject;

public class Local {
    public String type;
    public int intensity;
    public String impactAt;
    public Segment segment;

    public Local(JSONObject local) throws JSONException {
        this.intensity = local.getInt("intensity");
        this.type = local.getString("type");
        this.impactAt = local.getString("impactAt");
        this.segment = new Segment(local.getJSONObject("segment"));
    }
}
