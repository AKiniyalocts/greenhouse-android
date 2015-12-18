package com.rightmeowapps.greenthumb.data;


import com.rightmeowapps.greenthumb.model.Auth;
import com.rightmeowapps.greenthumb.model.Project;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anthonykiniyalocts on 11/4/15.
 */

@Module
public class RealmModule {

    @Singleton
    @Provides
    public RealmManager<Project> provideProjectRealmManager(){
        return new RealmManager<Project>(Project.class);
    }

    @Singleton
    @Provides
    public RealmManager<Auth> authRealmManager(){
        return new RealmManager<Auth>(Auth.class);
    }

}
