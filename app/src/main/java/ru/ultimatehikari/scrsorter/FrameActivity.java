package ru.ultimatehikari.scrsorter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class FrameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.listcontainer, new ListFragment(), "LIST")
                    .commit();
        }
        setContentView(R.layout.activity_frame);
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