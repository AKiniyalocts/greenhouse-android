package com.rightmeowapps.greenthumb;

import android.app.Application;
import android.support.annotation.Nullable;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rightmeowapps.greenthumb.data.ApplicationComponent;
import com.rightmeowapps.greenthumb.data.DaggerApplicationComponent;
import com.rightmeowapps.greenthumb.data.HasComponent;
import com.rightmeowapps.greenthumb.data.RealmModule;
import com.rightmeowapps.greenthumb.model.API;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by anthonykiniyalocts on 11/1/15.
 */
public class GreenThumbApp extends Application implements HasComponent<ApplicationComponent>{

    public static GreenThumbApp instance;

    private API api;

    private static final int DISK_CACHE_SIZE = 50 * 1024 * 1024;


    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initApplicationComponent();

        initRealm();

    }

    @Override
    public ApplicationComponent buildComponent() {
        return component;
    }

    private void initApplicationComponent() {
        component = DaggerApplicationComponent.builder()
                .realmModule(new RealmModule())
                .build();
        component.inject(this);
    }

    @Override
    public ApplicationComponent getComponent() {
        return component;
    }


    private void initRealm() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private API initRetrofit(@Nullable final String token, @Nullable final String release) {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setCache(new Cache(GreenThumbApp.instance.getCacheDir(), DISK_CACHE_SIZE));
        okHttpClient.interceptors().add(new LogginInterceptor());

        okHttpClient.networkInterceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request = request.newBuilder()
                        .addHeader("token", token)
                        .addHeader("release", release)
                        .build();
                return chain.proceed(request);
            }
        });

        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return  retrofit.create(API.class);
    }

    public API getNewAPI(@Nullable String token, @Nullable String release){
        api = initRetrofit(token, release);
        return api;
    }

}
