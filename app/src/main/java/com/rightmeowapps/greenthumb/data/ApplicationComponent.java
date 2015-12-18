package com.rightmeowapps.greenthumb.data;

import com.rightmeowapps.greenthumb.GreenThumbApp;
import com.rightmeowapps.greenthumb.model.Auth;
import com.rightmeowapps.greenthumb.model.Project;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by anthonykiniyalocts on 11/4/15.
 */

@Singleton
@Component(modules = {RealmModule.class})
public interface ApplicationComponent {

    RealmManager<Project> projectManager();

    RealmManager<Auth> authManager();

    void inject(GreenThumbApp application);


}
