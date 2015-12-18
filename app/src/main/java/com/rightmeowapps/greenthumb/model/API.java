package com.rightmeowapps.greenthumb.model;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by anthonykiniyalocts on 10/30/15.
 */
public interface API {

    String BASE_URL = "https://app.greenhouseci.com/api/";

    @GET("projects/{project_id}")
    Call<Project> getProject(@Path("project_id") String projectId);
}
