package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class Project extends RealmObject{

    @SerializedName("build_configs")
    private BuildConfig buildConfig;

    @SerializedName("created_at")
    private String createdAt;

    private Environment environment;

    @SerializedName("icon_url")
    private String iconUrl;

    @PrimaryKey
    private String id;

    @SerializedName("latest_build")
    private LatestBuild latestBuild;

    private String name;

    private boolean notify;

    private String platform;

    @SerializedName("repo_path")
    private String repoPath;

    private String repository;

    @SerializedName("updated_at")
    private String updatedAt;

    public BuildConfig getBuildConfig() {
        return buildConfig;
    }

    public void setBuildConfig(BuildConfig buildConfig) {
        this.buildConfig = buildConfig;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatestBuild getLatestBuild() {
        return latestBuild;
    }

    public void setLatestBuild(LatestBuild latestBuild) {
        this.latestBuild = latestBuild;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
