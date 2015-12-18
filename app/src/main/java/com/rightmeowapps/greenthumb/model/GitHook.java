package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class GitHook extends RealmObject{

    @SerializedName("hook_url")
    private String hookUrl;

    @SerializedName("repo_type")
    private String repoType;

    @SerializedName("setup_link")
    private String setupLink;


    public String getHookUrl() {
        return hookUrl;
    }

    public void setHookUrl(String hookUrl) {
        this.hookUrl = hookUrl;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
    }

    public String getSetupLink() {
        return setupLink;
    }

    public void setSetupLink(String setupLink) {
        this.setupLink = setupLink;
    }
}
