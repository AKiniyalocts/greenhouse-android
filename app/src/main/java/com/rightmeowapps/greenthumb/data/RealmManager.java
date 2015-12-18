package com.rightmeowapps.greenthumb.data;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by anthonykiniyalocts on 11/3/15.
 */
public class RealmManager<T extends RealmObject> {

    private Class clazz;

    public RealmManager(Class clazz){
        this.clazz = clazz;
    }

    public void copyOrUpdate(T realmObject){
        begin();

            getRealm().copyToRealmOrUpdate(realmObject);

        end();
    }

    public RealmResults<T> findAll(){
        RealmResults<T> realmResults;

        begin();

            realmResults = getRealm().where(clazz).findAll();

        end();

        return realmResults;
    }

    public T findFirst(String columnName, String key){
        RealmResults<T> realmResults;

        begin();

            realmResults = getRealm().where(clazz).equalTo(columnName, key).findAll();

        end();

        return realmResults.get(0);
    }

    private static Realm getRealm() {
        return Realm.getDefaultInstance();
    }

    private static void begin(){
        getRealm().beginTransaction();
    }

    private static void end(){
        getRealm().commitTransaction();
    }

}
