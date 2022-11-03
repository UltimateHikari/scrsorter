package ru.ultimatehikari.scrsorter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.databinding.ActivityRecyclerBinding;
import ru.ultimatehikari.scrsorter.model.Picture;
import ru.ultimatehikari.scrsorter.model.PictureService;

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding binding;
    private PictureAdapter adapter;
    private PictureService service;
    private Consumer<List<Picture>> picturesListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new PictureAdapter();

        var layoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);

        picturesListener = (pictures) -> {adapter.setPictures(pictures);};
        service = ((App)getApplication()).getService();

        service.addListener(picturesListener);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        service.removeListener(picturesListener);
    }
}