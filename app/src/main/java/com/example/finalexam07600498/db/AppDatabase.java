package com.example.finalexam07600498.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

@Database(entities = {LedgerItem.class}, exportSchema = false, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    private static final String DB_NAME = "user.db";

    private static AppDatabase instance;

    public abstract LedgerDao ledgerDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            DB_NAME
                    ).build();
        }
        return instance;
    }
}
