package com.rightmeowapps.greenthumb.data;

import android.support.v7.app.AppCompatActivity;


import dagger.Component;

/**
 * Created by anthonykiniyalocts on 11/5/15.
 */

@Component(dependencies = ApplicationComponent.class, modules= ActivityModule.class)
public interface ActivityComponent {

    void inject(AppCompatActivity activity);
}
