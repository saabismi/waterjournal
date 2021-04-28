package com.example.waterjournal.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

import com.example.waterjournal.MainActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.Registration;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Main fragment opens on default after user have set weight in registration activity
 */
public class HomeFragment extends Fragment {
    /**
     *
     */
    private Button addDrink, removeResent;
    private NumberPicker amounPicker;
    private TextView textAmount,textAmountGoal;
    public String drinks[] = {"100","200","250","300","330","400","450","500","600","700","800","900","1000"};

    /*
    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private boolean getRegistered; // variable for getting the value of registration status key
*/
    private String TAG = "WaterLog:"; // easy to use tag for logging

    /**
     * Metods and values
     * @return
     */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        /*preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        getRegistered = preferences.getBoolean(userRegistered, false);
        if (getRegistered != true) { // checking if the user has registered already
            Log.i(TAG, "User hasn't registered yet, let's go to Registration activity");
            Intent regIntent = new Intent(this, Registration.class); // create intent for going to Registration.java
            startActivity(regIntent); // start the activity Registration.java
        } else {
            Log.d(TAG, "User has already registered, continuing in MainActivity");
            //showWeight.setText(Integer.toString(getWeight) + " kg");
            //showTarget.setText(getTarget + " litres");
        }*/



        /** THIS CODE IS TO BE CHANGED WHEN THE PERSON-WATER-OBJECT BRANCH IS MERGED!!! */

        float amountGoal = Float.parseFloat(MainActivity.getTarget()) * 1000; // litres to millilitres
        int amountGoalInt = Math.round(amountGoal); // round it so that the decimal point leaves
        textAmountGoal = main.findViewById(R.id.textAmountGoal); // get the textivew for the field to use
        textAmountGoal.setText("Amount / " + amountGoalInt + " ml"); // set the text as the goal in millilitres

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
        amounPicker.setValue(2);
        amounPicker.setDisplayedValues(drinks);
            /**
             * Numberpicker value picker
             */
            amounPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                //Amount of drink to store
                String value = numberPicker.getDisplayedValues()[newVal];
                // Show amount and text on textAmount
                //this will be removed when store is in use
                textAmount.setText(" ml");
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