package ru.ultimatehikari.scrsorter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onBtnClick(View view) {
        Log.i("BTN", "clicked");
        startActivity(new Intent(this, ListActivity.class));
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