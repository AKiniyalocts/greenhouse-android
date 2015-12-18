package com.rightmeowapps.greenthumb.model;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class BuildConfig extends RealmObject{

    private Debug debug;

    private boolean release;

    public Debug getDebug() {
        return debug;
    }

    public void setDebug(Debug debug) {
        this.debug = debug;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }
}
