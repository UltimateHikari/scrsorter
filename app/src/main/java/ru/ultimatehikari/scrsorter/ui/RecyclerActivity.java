package ru.ultimatehikari.scrsorter.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import ru.ultimatehikari.scrsorter.App;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.databinding.ActivityRecyclerBinding;
import ru.ultimatehikari.scrsorter.model.Picture;
import ru.ultimatehikari.scrsorter.model.PictureService;
import ru.ultimatehikari.scrsorter.viewmodel.PictureListViewModel;

public class RecyclerActivity extends AppCompatActivity {

    private ActivityRecyclerBinding binding;
    private PictureAdapter adapter;
//    private PictureService service;
    //private Consumer<List<Picture>> picturesListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PictureListViewModel viewModel =
                new ViewModelProvider(this).get(PictureListViewModel.class);

        subcribeToViewModel(viewModel.getPictures());

        binding = ActivityRecyclerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        adapter = new PictureAdapter();

        var layoutManager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);

        //picturesListener = (pictures) -> {adapter.setPictures(pictures);};

//        service = ((App)getApplication()).getService();
//
//        service.addListener(picturesListener);

    }

    private void subcribeToViewModel(LiveData<List<PictureEntity>> liveData) {
        liveData.observe(this, pictures -> {
            adapter.setPictures(pictures);
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //service.removeListener(picturesListener);
    }
}