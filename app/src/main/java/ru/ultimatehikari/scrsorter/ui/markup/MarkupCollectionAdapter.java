package ru.ultimatehikari.scrsorter.ui.markup;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Collections;
import java.util.List;

import ru.ultimatehikari.scrsorter.data.entity.PictureEntityWithCategories;
import ru.ultimatehikari.scrsorter.viewmodel.MarkupViewModel;

public class MarkupCollectionAdapter extends FragmentStateAdapter {

    private List<PictureEntityWithCategories> pictures = Collections.emptyList();

    public MarkupCollectionAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new MarkupObjectFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        args.putInt(MarkupObjectFragment.ARG_OBJECT, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return Math.min(pictures.size(), 10);
    }

    public void setPictures(List<PictureEntityWithCategories> pictures) {
        this.pictures = pictures;
        Log.i("IMG", "changed!!!" + pictures.size());
        notifyDataSetChanged();
    }
}
