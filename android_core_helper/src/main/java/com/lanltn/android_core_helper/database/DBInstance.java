package com.lanltn.android_core_helper.database;

import android.content.Context;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;

public class DBInstance {

    private static DBInstance instance;
    private Realm realm;

    public DBInstance() {

    }

    public static DBInstance getInstance() {
        if (instance == null)
            instance = new DBInstance();
        return instance;
    }

    /**
     * Add this method in Application class
     *
     * @param context
     */
    public void init(Context context, RealmMigration realmMigration) {
        init(context, "default.realm", realmMigration);
    }

    public void init(Context context, String dbName, RealmMigration realmMigration) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(dbName)
                .schemaVersion(1)
                .migration(realmMigration)
                .build();
        realm = Realm.getInstance(config);
    }
}
