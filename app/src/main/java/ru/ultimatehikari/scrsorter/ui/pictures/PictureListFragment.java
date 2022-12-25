package ru.ultimatehikari.scrsorter.ui.pictures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.ultimatehikari.scrsorter.data.MockGenerator;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;
import ru.ultimatehikari.scrsorter.databinding.FragmentPicturesListBinding;
import ru.ultimatehikari.scrsorter.ui.category.CategoryAdapter;
import ru.ultimatehikari.scrsorter.ui.pictures.PictureAdapter;
import ru.ultimatehikari.scrsorter.viewmodel.PictureListViewModel;

public class PictureListFragment extends Fragment {

    private FragmentPicturesListBinding binding;
    private PictureAdapter adapter;
    private PictureListViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         viewModel = new ViewModelProvider(requireActivity()).get(PictureListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPicturesListBinding.inflate(inflater);

        var layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);

        setRecyclerView();

        Long category_id = getArguments().getLong("categoryId");

        subscribeToViewModel(viewModel.getPicturesById(category_id));

        return binding.getRoot();
    }

    private void setRecyclerView() {
        if(adapter == null){
            adapter = new PictureAdapter();
        }
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void subscribeToViewModel(LiveData<List<PictureEntityWithCategories>> liveData) {
        liveData.observe(getViewLifecycleOwner(), pictures -> {
            adapter.setPictures(pictures);
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}