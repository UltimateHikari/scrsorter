package ru.ultimatehikari.scrsorter.ui.markup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.helper.widget.Flow;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.work.Data;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import ru.ultimatehikari.scrsorter.R;
import ru.ultimatehikari.scrsorter.data.entity.CategoryEntity;
import ru.ultimatehikari.scrsorter.databinding.SingleTagBinding;
import ru.ultimatehikari.scrsorter.model.Category;
import ru.ultimatehikari.scrsorter.model.Picture;
import ru.ultimatehikari.scrsorter.model.PictureWithCategories;
import ru.ultimatehikari.scrsorter.ui.AddCategoryFragment;
import ru.ultimatehikari.scrsorter.viewmodel.CategoryListViewModel;
import ru.ultimatehikari.scrsorter.viewmodel.MarkupViewModel;

public class MarkupObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    Integer position = 0;

    private MarkupViewModel model = null;
    private LayoutInflater inflater;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(model == null){
            model = new ViewModelProvider(requireActivity()).get(MarkupViewModel.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        this.inflater = inflater;
        return inflater.inflate(R.layout.fragment_markup_object, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        var text =
        ((TextView) view.findViewById(R.id.text1));
                if(text != null && args != null){
                    position = args.getInt(ARG_OBJECT);
                    text.setText(Integer.toString(position));
                }
                setMainAdapter(view);

        var button =
                ((Button) view.findViewById(R.id.apply_button));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.updateTags(getContext(), collectFormData(), requireActivity());
                Snackbar.make(view, "Tags will be updated asynchronously", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bindPicture(view, model.getPictures().getValue().get(position));

        bindTags(model.getPictures().getValue().get(position), position);

        Spinner spinner = view.findViewById(R.id.main_button);
        var add_button =
                ((Button) view.findViewById(R.id.add_button));
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pushTag((CategoryEntity) spinner.getSelectedItem());
            }
        });
    }

    public void bindPicture(View view, Picture picture) {
        ImageView pictureView = view.findViewById(R.id.image);
        assert pictureView != null;
        if(!picture.getUrl().isEmpty()){
            Glide.with(pictureView.getContext())
                    .load(picture.getUrl())
                    .circleCrop()
                    .placeholder(R.drawable.no_mlny)
                    .error(R.drawable.no_mlny)
                    .into(pictureView);
        }else{
            pictureView.setImageResource(R.drawable.no_mlny);
        }
    }

    private Data collectFormData() {
        Spinner spinner = getView().findViewById(R.id.main_button);
        var picture = model.getPictures().getValue().get(position);
        picture.setCategory((CategoryEntity) spinner.getSelectedItem());
        picture.setMinorCategories(extractTags());

        Gson gson = new Gson();
        String key = gson.toJson(picture);

        return new Data.Builder()
                .putString("GsonnedImage", key)
                .build();
    }

    private int getCategoryId() {
        return 2;
    }

    private int getPictureId() {
        return 1;
    }

    private void setMainAdapter(@NonNull View view){
        Spinner spinner = view.findViewById(R.id.main_button);
        var list = model.getCategories();
        ArrayAdapter<CategoryEntity> adapter =
                new ArrayAdapter<CategoryEntity>(getContext(), android.R.layout.simple_spinner_dropdown_item, list.getValue());
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
//                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection((model.getPictures().getValue().get(position).category.categoryId.intValue() - 1) % spinner.getCount());
    }

    public void bindTags(PictureWithCategories picture, int position) {
        Flow flow = getView().findViewById(R.id.tags_flow);
        var tags = new LinkedList<TextView>();

        removePreviousTags();
        for(Category i : picture.getMinorCategories()){
            var tag = SingleTagBinding
                    .inflate(inflater, null,false)
                    .getRoot();
            tag.setId(View.generateViewId());
            tag.setText(i.getName());
            tags.add(tag);
            ((ConstraintLayout)getView()).addView(tag);
        }
        flow.setReferencedIds(
                tags.stream().map(View::getId).mapToInt(i -> i).toArray()
        );
    }

    private void removePreviousTags() {
        Flow flow = getView().findViewById(R.id.tags_flow);
        var ids = flow.getReferencedIds();
        for (int i:
                ids) {
            var v = getView().findViewById(i);
            ((ConstraintLayout)getView()).removeView(v);
        }
    }

    private void pushTag(CategoryEntity category){
        Flow flow = getView().findViewById(R.id.tags_flow);
        var ids = flow.getReferencedIds();
        var newids = Arrays.copyOf(ids, ids.length + 1);
        var generatedId = View.generateViewId();
        newids[ids.length] = generatedId;

        var tag = SingleTagBinding
                .inflate(inflater, null,false)
                .getRoot();
        tag.setId(generatedId);
        tag.setText(category.getName());
        tag.setHint(category.getCategoryId().toString());
        ((ConstraintLayout)getView()).addView(tag);

        flow.setReferencedIds(newids);
    }

    private List<CategoryEntity> extractTags(){
        Flow flow = getView().findViewById(R.id.tags_flow);
        var ids = flow.getReferencedIds();
        var list = new ArrayList<CategoryEntity>();
        for(int i : ids){
            var cat = new CategoryEntity();
            TextView v = getView().findViewById(i);
            cat.setName(v.getText().toString());
            cat.setCategoryId(Long.parseLong(v.getHint().toString()));
            list.add(cat);
        }
        return list;
    }
}
