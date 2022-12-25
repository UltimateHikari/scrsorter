package ru.ultimatehikari.scrsorter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.Data;

import com.google.android.material.snackbar.Snackbar;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.databinding.FragmentAddCategoryBinding;
import ru.ultimatehikari.scrsorter.viewmodel.AddCategoryViewModel;
import ru.ultimatehikari.scrsorter.viewmodel.CategoryListViewModel;

public class AddCategoryFragment extends Fragment {

    private FragmentAddCategoryBinding binding;

    private AddCategoryViewModel model;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(model == null){
            model = new ViewModelProvider(requireActivity()).get(AddCategoryViewModel.class);
        }
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

      binding = FragmentAddCategoryBinding.inflate(inflater, container, false);
      return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.addCategory(getContext(), collectFormData());
                Snackbar.make(view, "Category will be created asynchronously", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                NavHostFragment.findNavController(AddCategoryFragment.this)
                       .navigate(R.id.action_FirstFragment_to_categoryListFragment);
            }
        });
    }

    private Data collectFormData() {
        return new Data.Builder()
                .putString("CATEGORY_NAME", binding.input.getText().toString())
                .build();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}