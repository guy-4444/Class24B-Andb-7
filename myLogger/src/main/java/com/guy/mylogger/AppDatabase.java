package com.guy.mylogger;

import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {MinLog.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MinLogDao minLogDao();

}
