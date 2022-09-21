package ru.ultimatehikari.scrsorter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class ListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Spinner spinner = findViewById(R.id.planets_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("START", this.toString());
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("RESUME", this.toString());
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("PAUSE", this.toString());
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("STOP", this.toString());
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("DESTROY", this.toString());
    }
}