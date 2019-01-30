package com.hva.joris.seriestracker.activities;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.hva.joris.seriestracker.AppDatabase;
import com.hva.joris.seriestracker.MainViewModel;
import com.hva.joris.seriestracker.R;
import com.hva.joris.seriestracker.models.SerieObject;
import com.hva.joris.seriestracker.adapters.SerieObjectAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SerieObjectAdapter.SerieClickListener {
    private static final int REQUESTCODE_EDIT = 1234;
    private static final int REQUESTCODE_ADD = 4321;
    public static final String EXTRA_SERIE = "SERIE";

    public static AppDatabase appDatabase;

    private List<SerieObject> serieObjects = new ArrayList<>();
    private SerieObjectAdapter adapter;
    private MainViewModel mMainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Create the recyclerview and set it's layout manager
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        //Instantiate the adapter and add it to the recycleView
        adapter = new SerieObjectAdapter(serieObjects, getResources(), this);
        recyclerView.setAdapter(adapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mMainViewModel = new MainViewModel(getApplicationContext());
        mMainViewModel.getSeries().observe(this, new Observer<List<SerieObject>>() {
            @Override
            public void onChanged(@Nullable List<SerieObject> mSerieObjects) {
                serieObjects = mSerieObjects;
                updateUI();
            }
        });

        FloatingActionButton addSerieButton = findViewById(R.id.addSerieButton);
        addSerieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,SelectSerieActivity.class),REQUESTCODE_ADD);
            }
        });

        //Swipe right to remove the serieObject
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                int position = viewHolder.getAdapterPosition();
                SerieObject serieObject = serieObjects.get(position);
                mMainViewModel.delete(serieObject);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == REQUESTCODE_ADD){
                SerieObject serieObject = data.getParcelableExtra(EXTRA_SERIE);
                mMainViewModel.insert(serieObject);
            }
            else if(requestCode == REQUESTCODE_EDIT) {
                SerieObject serieObject = data.getParcelableExtra(EXTRA_SERIE);
                mMainViewModel.update(serieObject);
            }
        }
    }

    public void updateUI(){
        adapter.swapList(serieObjects);
    }

    @Override
    public void serieOnClick(int i) {
        SerieObject serieObject = serieObjects.get(i);
        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra(EXTRA_SERIE, serieObject);
        startActivityForResult(intent, REQUESTCODE_EDIT);
    }

}
