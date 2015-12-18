package com.rightmeowapps.greenthumb.data;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by anthonykiniyalocts on 11/5/15.
 */
@Module
public class ActivityModule {
    private final AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    public AppCompatActivity providesActivity(){
        return appCompatActivity;
    }
}
