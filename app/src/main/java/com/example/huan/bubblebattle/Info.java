package com.example.huan.bubblebattle;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by huan on 2016/1/2.
 */
public class Info {
    public String title;
    public String value;
    public Info (String title, String value )
    {
        this.title = title;
        this.value = value;
    }
    public Info(JSONObject object){
        try {
            this.title = object.getString("title");
            this.value = object.getString("value");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Factory method to convert an array of JSON objects into a list of objects
    // User.fromJson(jsonArray);
    public static ArrayList<Info> fromJson(JSONArray jsonObjects) {
        ArrayList<Info> infos = new ArrayList<Info>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                infos.add(new Info(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return infos;
    }
}
