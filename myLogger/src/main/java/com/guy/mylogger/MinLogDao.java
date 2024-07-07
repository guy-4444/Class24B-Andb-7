package com.guy.mylogger;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MinLogDao {

    @Insert
    void insertAll(MinLog... minLogs);

    @Delete
    void delete(MinLog minLog);

    @Update
    void updateLogs(MinLog... minLogs);

    @Query("SELECT * FROM min_data")
    List<MinLog> getAll();

    @Query("SELECT * FROM min_data where tag LIKE :tag")
    List<MinLog> getAllByTag(String tag);

    @Query("SELECT * FROM min_data where tag LIKE :tag AND time BETWEEN :start AND :end")
    List<MinLog> getAllLogsByTagBetweenDates(long start, long end, String tag);

    @Query("SELECT * FROM min_data where tag LIKE :tag AND time > :start")
    List<MinLog> getAllLogsByTagFromDate(long start, String tag);

    @Query("DELETE FROM min_data WHERE 1")
    void deleteAll();

}
