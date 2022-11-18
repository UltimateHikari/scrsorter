package ru.ultimatehikari.scrsorter.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;

import ru.ultimatehikari.scrsorter.data.MockGenerator;
import ru.ultimatehikari.scrsorter.databinding.FragmentCategoryListBinding;

public class CategoryListFragment extends Fragment {

    private static final int MAGIC_INT = 101;
    private FragmentCategoryListBinding binding;
    private CategoryAdapter adapter;

    public CategoryListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryListBinding.inflate(inflater);
        adapter = new CategoryAdapter();
        var layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);
        binding.recycler.setAdapter(adapter);

        binding.testNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireNotification();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter.setCategories(MockGenerator.generateCategories());
    }
    
    private void fireNotification(){
        var activity = getActivity();
        if(activity == null){
            return;
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(activity);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(MAGIC_INT, ((AppActivity)activity).getBuilder().build());
    }
}