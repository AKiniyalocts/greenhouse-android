package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class Environment extends RealmObject{

    @SerializedName("created_at")
    private String createdAt;


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
