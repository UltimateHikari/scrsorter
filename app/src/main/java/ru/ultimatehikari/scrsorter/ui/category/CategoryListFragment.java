package ru.ultimatehikari.scrsorter.ui.category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.data.MockGenerator;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.data.entity.PictureEntity;
import ru.ultimatehikari.scrsorter.databinding.FragmentCategoryListBinding;
import ru.ultimatehikari.scrsorter.ui.AppActivity;
import ru.ultimatehikari.scrsorter.ui.category.CategoryAdapter;
import ru.ultimatehikari.scrsorter.viewmodel.CategoryListViewModel;
import ru.ultimatehikari.scrsorter.viewmodel.PictureListViewModel;

public class CategoryListFragment extends Fragment {

    private static final int MAGIC_INT = 101;
    private FragmentCategoryListBinding binding;
    private CategoryAdapter adapter;
    private CategoryListViewModel viewModel;

    public CategoryListFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(CategoryListViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCategoryListBinding.inflate(inflater);
        var layoutManager = new LinearLayoutManager(getContext());
        binding.recycler.setLayoutManager(layoutManager);
        setRecyclerView();


        binding.testNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fireNotification();
            }
        });

        binding.addCategoty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CategoryListFragment.this)
                        .navigate(R.id.action_categoryListFragment_to_FirstFragment);
            }
        });

        subscribeToViewModel(viewModel.getCategories());

        return binding.getRoot();
    }

    private void setRecyclerView() {
        if(adapter == null){
            adapter = new CategoryAdapter();

        }
        binding.recycler.setAdapter(adapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //adapter.setCategories(MockGenerator.generateCategories());
    }

    private void subscribeToViewModel(LiveData<List<CategoryEntity>> liveData) {
        liveData.observe(getViewLifecycleOwner(), categories -> {
            Log.i("ADDING", categories.toString());
            adapter.setCategories(categories);
        });
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