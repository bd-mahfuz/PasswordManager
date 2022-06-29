package com.jfaq.passmanager.data;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jfaq.passmanager.data.dao.CredentialCategoryDao;
import com.jfaq.passmanager.data.dao.CredentialInfoDao;
import com.jfaq.passmanager.data.dao.UserDao;
import com.jfaq.passmanager.data.dao.VaultDao;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.data.entities.User;
import com.jfaq.passmanager.data.entities.Vault;
import com.jfaq.passmanager.util.AppConstant;

import java.io.File;
import java.util.concurrent.Executors;

@Database(entities = {User.class, CredentialCategory.class, CredentialInfo.class, Vault.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "pass_manager";

    private static AppDatabase appDatabase;
    private static Context ctx;
    public abstract UserDao userDao();
    public abstract CredentialCategoryDao credentialCategoryDao();
    public abstract CredentialInfoDao credentialInfoDao();
    public abstract VaultDao vaultDao();


    public static AppDatabase getAppDatabase(Context context) {

        ctx = context;
        //deleteDatabaseFile("pass_manager");
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                     AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(dbCallback)
                    .build();
        }
        return appDatabase;
    }

    public static void destroy(){
        appDatabase = null;
    }

    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                @Override
                public void run() {
                    Log.i( "dbCallback ", "inserting category list.....");
                    getAppDatabase(ctx).credentialCategoryDao()
                            .saveAll(AppConstant.credentialCategory);
                }
            });
        }
    };

    public static void deleteDatabaseFile(String databaseName) {
        File databases =
                new File(ctx.getApplicationInfo().dataDir + "/databases");
        File db = new File(databases, databaseName);

        if (db.delete())
            Log.d("Database deleted ", "");
        else
            Log.d("Failed delete", "");
    }



}
