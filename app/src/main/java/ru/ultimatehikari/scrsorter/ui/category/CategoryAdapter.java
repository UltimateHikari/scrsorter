package ru.ultimatehikari.scrsorter.ui.category;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.SingleCategoryBinding;
import ru.ultimatehikari.scrsorter.model.Category;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<? extends Category> categories = Collections.emptyList();

    void setCategories(List<? extends Category> newCategories){
        categories = newCategories;
        Log.i("setCategories", categories.stream().map(i -> {return i.getName();}).collect(Collectors.joining()));
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var inflater = LayoutInflater.from(parent.getContext());
        var binding = SingleCategoryBinding.inflate(inflater, parent, false);
        return new CategoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        var category = categories.get(position);

        holder.singleCategoryBinding.text.setText(category.getName());

        holder.singleCategoryBinding.text.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_categoryListFragment_to_pictureListFragment)
        );

//        holder.singleCategoryBinding.text.setOnClickListener(
//                Navigation.createNavigateOnClickListener(R.id.action_categoryListFragment_to_FirstFragment)
//        );

//        holder.singleCategoryBinding.text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                NavHostFragment.findNavController(getParentFragment())
//                        .navigate(R.id.action_SecondFragment_to_categoryListFragment);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        private final SingleCategoryBinding singleCategoryBinding;

        public CategoryViewHolder(SingleCategoryBinding bind) {
            super(bind.getRoot());
            singleCategoryBinding = bind;
        }
    }

}
