package com.jfaq.passmanager.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.jfaq.passmanager.data.dao.CredentialCategoryDao;
import com.jfaq.passmanager.data.dao.CredentialInfoDao;
import com.jfaq.passmanager.data.dao.UserDao;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.data.entities.User;

@Database(entities = {User.class, CredentialCategory.class, CredentialInfo.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "pass_manager";

    private static AppDatabase appDatabase;
    public abstract UserDao userDao();
    public abstract CredentialCategoryDao credentialCategoryDao();
    public abstract CredentialInfoDao credentialInfoDao();

    public static AppDatabase getAppDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return appDatabase;
    }

    public static void destroy(){
        appDatabase = null;
    }



}
