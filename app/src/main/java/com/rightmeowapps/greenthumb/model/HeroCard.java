package com.rightmeowapps.greenthumb.model;

/**
 * Created by anthonykiniyalocts on 11/2/15.
 */
public class HeroCard {

    public static HeroCard buildFromProject(Project project){
        HeroCard heroCard = new HeroCard();

        heroCard.setProjectTitle(project.getName());
        heroCard.setProjectIconUrl(project.getIconUrl());
        heroCard.setProjectPlatform(project.getPlatform());
        heroCard.setLatestBuild(project.getLatestBuild());

        return heroCard;
    }


    private String projectTitle;

    private String projectIconUrl;

    private String projectPlatform;

    private LatestBuild latestBuild;

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectIconUrl() {
        return projectIconUrl;
    }

    public void setProjectIconUrl(String projectIconUrl) {
        this.projectIconUrl = projectIconUrl;
    }

    public String getProjectPlatform() {
        return projectPlatform;
    }

    public void setProjectPlatform(String projectPlatform) {
        this.projectPlatform = projectPlatform;
    }

    public LatestBuild getLatestBuild() {
        return latestBuild;
    }

    public void setLatestBuild(LatestBuild latestBuild) {
        this.latestBuild = latestBuild;
    }
}
