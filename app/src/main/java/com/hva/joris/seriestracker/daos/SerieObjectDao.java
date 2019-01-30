package com.hva.joris.seriestracker.daos;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.hva.joris.seriestracker.models.SerieObject;

import java.util.List;

//Creates specific commands for interacting with the database
@Dao
public interface SerieObjectDao {

    @Query("SELECT * FROM serie")
    public LiveData<List<SerieObject>> getAllSeries();

    @Insert
    public void insertSeries(SerieObject serie);

    @Delete
    public void deleteSeries(SerieObject serie);

    @Update
    public void updateSeries(SerieObject serie);

}
