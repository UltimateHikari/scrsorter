package ru.ultimatehikari.scrsorter.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.SinglePictureBinding;
import ru.ultimatehikari.scrsorter.model.Picture;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PicturesViewHolder> {

    List<? extends Picture> pictures = Collections.emptyList();

    void setPictures(List<? extends Picture> newPictures){
        pictures = newPictures;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PicturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        var inflater = LayoutInflater.from(parent.getContext());
        var binding = SinglePictureBinding.inflate(inflater, parent, false);
        return new PicturesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesViewHolder holder, int position) {
        var picture = pictures.get(position);

        holder.singlePictureBinding.name.setText(picture.getName());
        holder.singlePictureBinding.details.setText(picture.getName());
        holder.singlePictureBinding.more.setOnClickListener(view -> {
            Log.i("LOG_TAG", "Full Name: " + picture.getName());
        });
        if(!picture.getUrl().isEmpty()){
            Glide.with(holder.singlePictureBinding.picture.getContext())
                    .load(picture.getUrl())
                    .circleCrop()
                    .placeholder(R.drawable.ic_baseline_person_24)
                    .error(R.drawable.ic_baseline_person_24)
                    .into(holder.singlePictureBinding.picture);
        }else{
            holder.singlePictureBinding.picture.setImageResource(R.drawable.ic_baseline_person_24);
        }
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public static class PicturesViewHolder extends RecyclerView.ViewHolder {
        private final SinglePictureBinding singlePictureBinding;

        public PicturesViewHolder(SinglePictureBinding bind) {
            super(bind.getRoot());
            singlePictureBinding = bind;
        }
    }

}
