package ru.ultimatehikari.scrsorter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.ultimatehikari.scrsorter.ui.AppActivity;
import ru.ultimatehikari.scrsorter.ui.RecyclerActivity;
import ru.ultimatehikari.scrsorter.ui.SettingsActivity;
import ru.ultimatehikari.scrsorter.ui.lessons.FrameActivity;
import ru.ultimatehikari.scrsorter.ui.lessons.ListActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onLBtnClick(View view) {
        Log.i("BTN", "clicked");
        startActivity(new Intent(this, ListActivity.class));
    }

    public void onFBtnClick(View view) {
        Log.i("BTN", "clicked");
        startActivity(new Intent(this, FrameActivity.class));
    }

    public void onRBtnClick(View view) {
        Log.i("BTN", "clicked");
        startActivity(new Intent(this, RecyclerActivity.class));
    }

    public void onABtnClick(View view) {
        Log.i("BTN", "clicked");
        startActivity(new Intent(this, AppActivity.class));
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