package ru.ultimatehikari.scrsorter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.FragmentAddCategoryBinding;

public class AddCategoryFragment extends Fragment {

private FragmentAddCategoryBinding binding;

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
                Snackbar.make(view, "Imagine adding category in dummy project :P", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
//                NavHostFragment.findNavController(AddCategoryFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_categoryListFragment);
            }
        });
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}