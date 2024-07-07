package com.guy.mylogger;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "min_data")
public class MinLog {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "tag")
    public String tag = "";

    @ColumnInfo(name = "text")
    public String message = "";

    @ColumnInfo(name = "time")
    public long time = 0;

    @ColumnInfo(name = "priority")
    public int priority = 1;

    @Ignore
    public boolean isSelected = false;

    public MinLog() {}

    public MinLog(String tag, String message, long time) {
        this.tag = tag;
        this.message = message;
        this.time = time;
    }

    public String getTag() {
        return tag;
    }

    public MinLog setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MinLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public long getTime() {
        return time;
    }

    public MinLog setTime(long time) {
        this.time = time;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public MinLog setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    @Override
    public String toString() {
        return "MinLog{" +
                "tag='" + tag + '\'' +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", priority=" + priority +
                '}';
    }
}
