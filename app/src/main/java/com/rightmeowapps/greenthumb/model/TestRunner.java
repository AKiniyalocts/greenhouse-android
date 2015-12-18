package com.rightmeowapps.greenthumb.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class TestRunner extends RealmObject{

    private boolean active;

    @SerializedName("config_id")
    private String configId;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("deleted_at")
    private String deletedAt;

    @SerializedName("framework_type")
    private String frameworkType;

    private String id;

    private String target;

    @SerializedName("updated_at")
    private String updatedAt;

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getFrameworkType() {
        return frameworkType;
    }

    public void setFrameworkType(String frameworkType) {
        this.frameworkType = frameworkType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
