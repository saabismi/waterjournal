package com.example.waterjournal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.R;

public class HomeFragment extends Fragment {

    private  Button addDrink, removeResent;
    private NumberPicker amounPicker;
    private TextView textAmount;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        /**
         * Button to add amount of drink to store
         */
        addDrink = main.findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        amounPicker = main.findViewById(R.id.amountPicker);
        textAmount = main.findViewById(R.id.textAmount);

        amounPicker.setMinValue(100);
        amounPicker.setMaxValue(1000);
        amounPicker.setValue(333);

            amounPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                textAmount.setText(i1 + " (ml)");
            }
        });

        /**
         * Button to remove resent drink from store
         */
        removeResent = main.findViewById(R.id.buttonRemoveResent);
        removeResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return main;
    }
}