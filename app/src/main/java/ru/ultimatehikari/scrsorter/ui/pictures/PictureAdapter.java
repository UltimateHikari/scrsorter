package ru.ultimatehikari.scrsorter.ui.pictures;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavHostController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.SinglePictureBinding;
import ru.ultimatehikari.scrsorter.databinding.SingleTagBinding;
import ru.ultimatehikari.scrsorter.model.Category;
import ru.ultimatehikari.scrsorter.model.Picture;
import ru.ultimatehikari.scrsorter.model.PictureWithCategories;
import ru.ultimatehikari.scrsorter.ui.category.CategoryListFragment;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PicturesViewHolder> {

    List<? extends PictureWithCategories> pictures = Collections.emptyList();

    void setPictures(List<? extends PictureWithCategories> newPictures){
        pictures = newPictures;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var inflater = LayoutInflater.from(parent.getContext());
        var binding = SinglePictureBinding.inflate(inflater, parent, false);

        return new PicturesViewHolder(binding, inflater);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesViewHolder holder, int position) {
        var picture = pictures.get(position);
        holder.bindData(picture);
        holder.bindPicture(picture);
        holder.bindTags(picture, position);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public static class PicturesViewHolder extends RecyclerView.ViewHolder {
        private final SinglePictureBinding singlePictureBinding;
        private final LayoutInflater inflater;

        public PicturesViewHolder(SinglePictureBinding bind, LayoutInflater inflater) {
            super(bind.getRoot());
            singlePictureBinding = bind;
            this.inflater = inflater;
        }


        public void bindData(PictureWithCategories picture) {
            singlePictureBinding.name.setText(picture.getName());
            singlePictureBinding.details.setText(picture.getCategory().getName());
            var bun = new Bundle();
            bun.putString("pictureUrl", picture.getUrl());
            singlePictureBinding.more.setOnClickListener(
                    Navigation.createNavigateOnClickListener(R.id.action_pictureListFragment_to_zoomImageViewFragment2,bun)
            );
        }

        public void bindPicture(Picture picture) {
            if(!picture.getUrl().isEmpty()){
                Glide.with(singlePictureBinding.picture.getContext())
                        .load(picture.getUrl())
                        .circleCrop()
                        .placeholder(R.drawable.no_mlny)
                        .error(R.drawable.no_mlny)
                        .into(singlePictureBinding.picture);
            }else{
                singlePictureBinding.picture.setImageResource(R.drawable.no_mlny);
            }
        }

        public void bindTags(PictureWithCategories picture, int position) {
            Flow flow = singlePictureBinding.getRoot().findViewById(R.id.flow);
            var tags = new LinkedList<TextView>();

            removePreviousTags();
            for(Category i : picture.getMinorCategories()){
                var tag = SingleTagBinding
                        .inflate(inflater, null,false)
                        .getRoot();
                tag.setId(View.generateViewId());
                tag.setText(i.getName());
                tags.add(tag);
                singlePictureBinding.getRoot().addView(tag);
            }
            flow.setReferencedIds(
                    tags.stream().map(View::getId).mapToInt(i -> i).toArray()
            );
        }

        private void removePreviousTags() {
            Flow flow = singlePictureBinding.getRoot().findViewById(R.id.flow);
            var ids = flow.getReferencedIds();
            for (int i:
                 ids) {
                var view = singlePictureBinding.getRoot().findViewById(i);
                singlePictureBinding.getRoot().removeView(view);
            }
        }
    }

}
