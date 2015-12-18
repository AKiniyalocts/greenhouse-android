package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class Debug extends RealmObject{

    @SerializedName("api_level")
    private String apiLevel;

    private String architecture;

    private String branch;

    private String config;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("git_hook")
    private GitHook gitHook;

    private String id;

    @SerializedName("latest_build")
    private LatestBuild latestBuild;

    @SerializedName("latest_duration")
    private int latestDuration;

    @SerializedName("project_file")
    private String projectFile;

    @SerializedName("project_id")
    private String projectId;

    private RealmList<Publisher> publishers;

    private boolean release;

    private String scheme;

    private String target;

    @SerializedName("test_runners")
    private RealmList<TestRunner> testRunners;

    private String type;

    @SerializedName("updated_at")
    private String updatedAt;


    public String getApiLevel() {
        return apiLevel;
    }

    public void setApiLevel(String apiLevel) {
        this.apiLevel = apiLevel;
    }

    public String getArchitecture() {
        return architecture;
    }

    public void setArchitecture(String architecture) {
        this.architecture = architecture;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public GitHook getGitHook() {
        return gitHook;
    }

    public void setGitHook(GitHook gitHook) {
        this.gitHook = gitHook;
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

    public int getLatestDuration() {
        return latestDuration;
    }

    public void setLatestDuration(int latestDuration) {
        this.latestDuration = latestDuration;
    }

    public String getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(String projectFile) {
        this.projectFile = projectFile;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public RealmList<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(RealmList<Publisher> publishers) {
        this.publishers = publishers;
    }

    public boolean isRelease() {
        return release;
    }

    public void setRelease(boolean release) {
        this.release = release;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public RealmList<TestRunner> getTestRunners() {
        return testRunners;
    }

    public void setTestRunners(RealmList<TestRunner> testRunners) {
        this.testRunners = testRunners;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
