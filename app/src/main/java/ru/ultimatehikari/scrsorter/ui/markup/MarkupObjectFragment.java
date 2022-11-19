package ru.ultimatehikari.scrsorter.ui.markup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import ru.ultimatehikari.scrsorter.R;

public class MarkupObjectFragment extends Fragment {
    public static final String ARG_OBJECT = "object";
    Integer position = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
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
    }

    private void setMainAdapter(@NonNull View view){
        Spinner spinner = view.findViewById(R.id.main_button);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(position % spinner.getCount());
    }
}
