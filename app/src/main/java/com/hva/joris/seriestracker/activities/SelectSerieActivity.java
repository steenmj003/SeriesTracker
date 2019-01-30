package com.hva.joris.seriestracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.hva.joris.seriestracker.R;
import com.hva.joris.seriestracker.models.Serie;
import com.hva.joris.seriestracker.adapters.SerieAdapter;
import com.hva.joris.seriestracker.SerieApiService;
import com.hva.joris.seriestracker.models.SerieList;
import com.hva.joris.seriestracker.models.SerieObject;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectSerieActivity extends AppCompatActivity implements SerieAdapter.SerieClickListener {
    private final String API_KEY = "0e2f893025b48f56f5b2fe552e81705d";

    private List<Serie> mSeries = new ArrayList<>();
    private SerieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_serie);

        //Create the recyclerview and set it's layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Instantiate the adapter and add it to the recycleView
        adapter = new SerieAdapter(mSeries, getResources(), this);
        recyclerView.setAdapter(adapter);

        requestData();
    }

    public void updateUI(){
        adapter.swapList(mSeries);
    }

    @Override
    public void serieOnClick(int i) {
        Serie serie = mSeries.get(i);

        SerieObject serieObject = serieToSerieObject(serie);
        Intent intent = new Intent();
        intent.putExtra(MainActivity.EXTRA_SERIE, serieObject);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    private void requestData()
    {
        SerieApiService service = SerieApiService.retrofit.create(SerieApiService.class);

        /**
         * Make an a-synchronous call by enqueing and definition of callbacks.
         */
        Call<SerieList> call = service.getSeries(API_KEY);
        call.enqueue(new Callback<SerieList>() {

            @Override
            public void onResponse(Call<SerieList> call, Response<SerieList> response) {
                SerieList series = response.body();
                mSeries = series.getSeries();
                updateUI();
            }

            @Override
            public void onFailure(Call<SerieList> call, Throwable t) {
                Log.d("errorjoris",t.toString());
            }
        });
    }

    private SerieObject serieToSerieObject(Serie serie)
    {
        return new SerieObject(serie.getName(), "", "Season 1, Episode 1", DateFormat.getDateInstance().format(new Date()));
    }

}
