package ru.ultimatehikari.scrsorter.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import ru.ultimatehikari.scrsorter.data.MockGenerator;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.databinding.FragmentPicturesListBinding;
import ru.ultimatehikari.scrsorter.viewmodel.PictureListViewModel;

public class PictureListFragment extends Fragment {

    private FragmentPicturesListBinding binding;
    private PictureAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PictureListViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(PictureListViewModel.class);

        subscribeToViewModel(viewModel.getPictures());

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPicturesListBinding.inflate(inflater);
        adapter = new PictureAdapter();

        var layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //TODO: remove
        adapter.setPictures(MockGenerator.generatePictures());
    }

    private void subscribeToViewModel(LiveData<List<PictureEntity>> liveData) {
        liveData.observe(this, pictures -> {
            adapter.setPictures(pictures);
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}