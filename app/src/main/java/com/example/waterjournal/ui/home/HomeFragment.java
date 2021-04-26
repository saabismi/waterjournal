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

import java.util.ArrayList;
import java.util.List;

/**
 * Main activity, opens on default after user have set weight in registration activity
 */
public class HomeFragment extends Fragment {
    /**
     *
     */
    private Button addDrink, removeResent;
    private NumberPicker amounPicker;
    private TextView textAmount;
    public String drinks[] = {"100","200","250","300","333","400","450","500","600","700","800","900","1000"};
    /**
     * Metods and values
     * @return
     */
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
            // Adds drink amount to store
            }
        });
        /**
         * Numberpicker to take user value and storing that to store
         */
        amounPicker = main.findViewById(R.id.amountPicker);
        textAmount = main.findViewById(R.id.textAmount);

        /**
         * Numberpicker min, max and default values
         * @param setMinValue lowest index on drink-array
         * @param setMaxValue highest indes on drinks-array
         * @param setDisplayedValues shows drinks-array values on picker
         */
        amounPicker.setMinValue(0);
        amounPicker.setMaxValue(12);
        amounPicker.setDisplayedValues(drinks);
            /**
             * Numberpicker value picker
             */
            amounPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                // Show amount and text on textAmount
                String value = numberPicker.getDisplayedValues()[newVal];
                textAmount.setText(value + " (ml)");
            }
        });

        /**
         * Button to remove resent drink from store
         */
        removeResent = main.findViewById(R.id.buttonRemoveResent);
        removeResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            // Removes resent value from store
            }
        });

        return main;
    }
}