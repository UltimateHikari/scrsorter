package ru.ultimatehikari.scrsorter.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.google.android.material.snackbar.Snackbar;

import ru.ultimatehikari.scrsorter.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        var view = super.onCreateView(inflater, container, savedInstanceState);
        Preference backup_button = findPreference(getString(R.string.backup_button_key));
        Preference delete_button = findPreference(getString(R.string.delete_button_key));

        assert backup_button != null;
        assert delete_button != null;

        backup_button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Snackbar.make(view, "WIP: Imagine you back-upped your screenshots", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });

        delete_button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Snackbar.make(view, "WIP: Imagine you saw a dialog and deleted your screenshots", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                return true;
            }
        });

        return view;
    }
}
