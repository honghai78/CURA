package com.example.haitran.cura.utils;

import android.util.Log;

import com.example.haitran.cura.objects.Personal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarException;

/**
 * Created by hai.tran on 6/30/2016.
 */
public class PersonalJsonReader {
    private static String TAG=PersonalJsonReader.class.getName();
    public static List<Personal> readJson(List<JSONObject> jsonObjects)
    {

        List<Personal> list = new ArrayList<>();
        try {
        for(int i=0; i<jsonObjects.size(); i++)
        {
            list.add(new Personal(jsonObjects.get(i).getString("name"), jsonObjects.get(i).getString("level"), jsonObjects.get(i).getString("phone")));
        }}
        catch (JSONException j)
        {
            Log.e(TAG, "ER: JSONException");
        }
        return list;
    }
}
