package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class LatestBuild extends RealmObject{

    @SerializedName("artefacts_url")
    private String artefactsUrl;

    @SerializedName("build_number")
    private int buildNumber;

    @SerializedName("commit_hash")
    private String commitHash;

    @SerializedName("config_id")
    private String configId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("deleted_at")
    private String deltedAt;

    @SerializedName("error")
    private String error;

    @SerializedName("finished_at")
    private String finishedAt;

    private String id;

    @SerializedName("lint_result_url")
    private String lintResultUrl;

    @SerializedName("project_id")
    private String projectId;

    private boolean release;

    @SerializedName("started_at")
    private String startedAt;

    private String status;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("version_code")
    private int versionCode;

    @SerializedName("version_name")
    private String versionName;


    public String getArtefactsUrl() {
        return artefactsUrl;
    }

    public void setArtefactsUrl(String artefactsUrl) {
        this.artefactsUrl = artefactsUrl;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public void setBuildNumber(int buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeltedAt() {
        return deltedAt;
    }

    public void setDeltedAt(String deltedAt) {
        this.deltedAt = deltedAt;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(String finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLintResultUrl() {
        return lintResultUrl;
    }

    public void setLintResultUrl(String lintResultUrl) {
        this.lintResultUrl = lintResultUrl;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public String getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }
}
