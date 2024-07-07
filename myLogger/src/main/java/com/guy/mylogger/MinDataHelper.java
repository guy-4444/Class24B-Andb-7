package com.guy.mylogger;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

public class MinDataHelper {

    private static MinDataHelper instance;
    private static AppDatabase appDatabase;

    private MinDataHelper(Context context) {
        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MinLogsDB.db")
                .addMigrations(MIGRATION_1_2)
                .build();

//        appDatabase = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "MinLogsDB.db")
//                // allow queries on the main thread.
//                // Don't do this on a real app! See PersistenceBasicSample for an example.
//                 .allowMainThreadQueries()
//                .build();
    }

    private Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE min_data ADD COLUMN priority INTEGER NOT NULL DEFAULT '1'");
        }
    };

    public static MinDataHelper getInstance() {
        return instance;
    }

    public static MinDataHelper initHelper(Context context) {
        if (instance == null) {
            instance = new MinDataHelper(context);
        }

        return instance;
    }


    public interface CallBack_Logs {
        void dataReady(List<MinLog> minLogs);
    }

    public void addLog(String tag, String message) {
        long time = System.currentTimeMillis();
        new Thread(() -> {
            appDatabase.minLogDao().insertAll(
                    new MinLog(tag, message, time)
            );
        }).start();
    }

    public void getAllLogsByTag(String tag, CallBack_Logs callBack_logs) {
        new Thread(() -> {
            List<MinLog> minLogs = appDatabase.minLogDao().getAllByTag(tag);
            if (callBack_logs != null) {
                callBack_logs.dataReady(minLogs);
            }
        }).start();
    }

    public void getAllLogs(CallBack_Logs callBack_logs) {
        new Thread(() -> {
            List<MinLog> minLogs = appDatabase.minLogDao().getAll();
            if (callBack_logs != null) {
                callBack_logs.dataReady(minLogs);
            }
        }).start();
    }
    public void getAllLogsFromByTag(CallBack_Logs callBack_logs, long timestamp, String tag) {
        new Thread(() -> {
            List<MinLog> minLogs = appDatabase.minLogDao().getAllLogsByTagFromDate(timestamp, tag);
            if (callBack_logs != null) {
                callBack_logs.dataReady(minLogs);
            }
        }).start();
    }

    public void deleteAllLogs() {
        new Thread(() -> {
            appDatabase.minLogDao().deleteAll();
        }).start();
    }
}
