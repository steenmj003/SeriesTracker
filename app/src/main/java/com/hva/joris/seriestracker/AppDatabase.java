package com.hva.joris.seriestracker;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.hva.joris.seriestracker.daos.SerieObjectDao;
import com.hva.joris.seriestracker.models.SerieObject;

@Database(entities = {SerieObject.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract SerieObjectDao serieObjectDao();
    private static final String DATABASE_NAME = "serie_objects";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME).build();
        }
        return instance;
    }
}
