package com.example.waterjournal.ui.preferences;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.R;

public class PreferencesFragment extends Fragment {

    private NumberPicker weightPicker, dailyPicker;
    private TextView textWeight, textAmount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View pref = inflater.inflate(R.layout.fragment_preferences, container, false);

        textWeight = pref.findViewById(R.id.textWeight);
        weightPicker = pref.findViewById(R.id.weightPicker);

        weightPicker.setMinValue(45);
        weightPicker.setMaxValue(250);
        weightPicker.setValue(70);

        weightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textWeight.setText(i1 + " (kg)");
            }
        });

        textAmount = pref.findViewById(R.id.textDaily);
        dailyPicker = pref.findViewById(R.id.drinkPicker);

        dailyPicker.setMinValue(100);
        dailyPicker.setMaxValue(1000);
        dailyPicker.setValue(333);

        dailyPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textAmount.setText(i1 + " (ml)");
            }
        });

        return pref;
    }
}