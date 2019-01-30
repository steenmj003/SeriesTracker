package com.hva.joris.seriestracker;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.hva.joris.seriestracker.models.SerieObject;
import com.hva.joris.seriestracker.repositories.SerieObjectRepository;

import java.util.List;


public class MainViewModel extends ViewModel {
    
    private SerieObjectRepository mRepository;
    private LiveData<List<SerieObject>> mSeries;
    
    public MainViewModel(Context context) {
        mRepository = new SerieObjectRepository(context);
        mSeries = mRepository.getAllSeries();
    }
    
    public LiveData<List<SerieObject>> getSeries() {
        return mSeries;
    }
    
    public void insert(SerieObject serieObject) {
        mRepository.insert(serieObject);
    }
    
    public void update(SerieObject serieObject) {
        mRepository.update(serieObject);
    }
    
    public void delete(SerieObject serieObject) {
        mRepository.delete(serieObject);
    }

}
