package ru.ultimatehikari.scrsorter.ui.markup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.view.InflateException;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.databinding.ActivityMarkupTabbedBinding;


public class MarkupTabbedActivity extends AppCompatActivity {

    MarkupCollectionAdapter adapter;
    ViewPager2 viewPager;
    SpringDotsIndicator dotsIndicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_markup_tabbed);
        }catch (InflateException e){
            Log.e("INFL", "oncreate", e);
            throw e;
        }

        if(savedInstanceState == null) {
            adapter = new MarkupCollectionAdapter(this);
            viewPager = findViewById(R.id.pager);
            viewPager.setAdapter(adapter);

            dotsIndicator = findViewById(R.id.dots_indicator);
            dotsIndicator.attachTo(viewPager);

        }
    }

}