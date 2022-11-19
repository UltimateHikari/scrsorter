package ru.ultimatehikari.scrsorter.ui.markup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MarkupCollectionAdapter extends FragmentStateAdapter {
    public MarkupCollectionAdapter(FragmentActivity fragmentActivity) {
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
        return 10;
    }
}
