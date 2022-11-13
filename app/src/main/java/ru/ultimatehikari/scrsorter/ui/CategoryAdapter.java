package ru.ultimatehikari.scrsorter.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.SingleCategoryBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    //TODO: move to CategoryEntity
    List<String> categories = Collections.emptyList();

    void setCategories(List<String> newCategories){
        categories = newCategories;
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

        holder.singleCategoryBinding.text.setText(category);

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
