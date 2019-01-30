package com.hva.joris.seriestracker.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.hva.joris.seriestracker.R;
import com.hva.joris.seriestracker.models.SerieObject;

import java.text.DateFormat;
import java.util.Date;

public class DetailsActivity extends AppCompatActivity {
    private SerieObject serieObject;
    private EditText title, platform, notes;
    private FloatingActionButton saveEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        serieObject = intent.getParcelableExtra(MainActivity.EXTRA_SERIE);

        title = findViewById(R.id.titleEditField);
        platform = findViewById(R.id.platformEditField);
        notes = findViewById(R.id.notesEditField);
        saveEdit = findViewById(R.id.saveEdit);

        //Fill the fields with the correct data if serieObject is not null
        if(serieObject != null) {
            title.setText(serieObject.getTitle());
            platform.setText(serieObject.getStatus());
            notes.setText(serieObject.getNotes());
        }
        else {
            serieObject = new SerieObject("","","", "");
        }

        final long id = serieObject.getId();

        saveEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fieldsNotEmpty()) {
                    SerieObject serieObject = new SerieObject(title.getText().toString(), platform.getText().toString(),notes.getText().toString(), DateFormat.getDateInstance().format(new Date()));
                    serieObject.setId(id);
                    Intent intent = new Intent();
                    intent.putExtra(MainActivity.EXTRA_SERIE, serieObject);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    Snackbar.make(v, "Please make sure all fields are filled in correctly", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private boolean fieldsNotEmpty() {
        return !title.getText().toString().isEmpty() && !platform.getText().toString().isEmpty() && !notes.getText().toString().isEmpty();
    }
}
