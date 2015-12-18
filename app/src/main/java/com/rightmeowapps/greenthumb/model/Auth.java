package com.rightmeowapps.greenthumb.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anthonykiniyalocts on 11/2/15.
 */
public class Auth extends RealmObject {

    @PrimaryKey
    private String id;

    private String token;

    private String release;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }
}
