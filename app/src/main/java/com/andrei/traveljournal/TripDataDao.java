package com.andrei.traveljournal;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TripDataDao {

    @Query("SELECT * FROM trips_table")
    LiveData<List<TripData>> getTripsData();

    @Query("SELECT * FROM trips_table WHERE isFavourite = 1")
    LiveData<List<TripData>> getFavouriteTripsData();

    @Query("DELETE FROM trips_table")
    void deleteAll();

    @Insert
    void insertTripData(TripData tripData);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllTripData(List<TripData> tripDataList);

    @Delete
    void deleteTripData(TripData tripData);

    @Delete
    void deleteTripsData(TripData... tripData);

    @Update
    void updateTripData(TripData tripData);

    @Update
    void updateTripsData(TripData... tripData);
}
