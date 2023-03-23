package com.example.inmyfridge.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.inmyfridge.data.model.DailyData;

import java.time.LocalDate;
import java.util.List;

@Dao
public interface DailyDataDAO {
    @Query("SELECT * FROM DAILYDATA")
    public List<DailyData> getAll();

    @Insert
    void insertAll(DailyData... dailyData);

    @Delete
    void delete(DailyData dailyData);

    @Update
    void update(DailyData dailyData);

    @Query("SELECT * FROM DailyData WHERE date = :date")
    DailyData getDataByID(LocalDate date);
}
