package com.hva.joris.seriestracker.repositories;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.hva.joris.seriestracker.AppDatabase;
import com.hva.joris.seriestracker.daos.SerieObjectDao;
import com.hva.joris.seriestracker.models.SerieObject;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SerieObjectRepository {

    private AppDatabase mAppDatabase;
    private SerieObjectDao mSerieDao;
    private LiveData<List<SerieObject>> mSeries;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public SerieObjectRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        mSerieDao = mAppDatabase.serieObjectDao();
        mSeries = mSerieDao.getAllSeries();
    }


    public LiveData<List<SerieObject>> getAllSeries() {
        return mSeries;
    }


    public void insert(final SerieObject serieObject) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mSerieDao.insertSeries(serieObject);
            }
        });
    }


    public void update(final SerieObject serieObject) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mSerieDao.updateSeries(serieObject);
            }
        });
    }


    public void delete(final SerieObject serieObject) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mSerieDao.deleteSeries(serieObject);
            }
        });
    }
}
