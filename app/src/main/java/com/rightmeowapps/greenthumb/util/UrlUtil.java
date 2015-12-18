package com.rightmeowapps.greenthumb.util;


import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class UrlUtil {

    private static final String TAG = UrlUtil.class.getSimpleName();

    public static String getProjectIdFromPath(String urlString){
        try {
            URL url = new URL(urlString);

            String[] paths = url.getPath().split("/");


            Log.d(TAG, "Path: " + url.getPath());
            Log.d(TAG,"Query: " + url.getQuery());

            return paths[3];


        }catch (MalformedURLException e) {

            e.printStackTrace();

        }

        return null;
    }


    public static String getQueryFromUrl(String urlString, String queryName){
        try {
            URL url = new URL(urlString);

            String[] queries = url.getQuery().split("&");

            String queryValue = null;

            for(String s: queries) {
                Log.d(TAG, s);

                if(s.startsWith(queryName)){
                    queryValue = s.substring(s.lastIndexOf("=") + 1);
                }
            }


            return queryValue;


        }catch (MalformedURLException e) {

            e.printStackTrace();

        }

        return null;
    }
}
