package com.example.waterjournal.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.Month;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.DailyDrinkingObject;
import com.example.waterjournal.MainActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.Registration;
import com.example.waterjournal.UserObject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Main fragment opens on default after user have set weight in registration activity
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    private Button addDrink, removeResent;
    private NumberPicker amounPicker;
    private TextView textAmount, textAmountGoal, textProgressBar;
    public String drinks[] = {"-", "100", "200", "250", "300", "333", "400", "450", "500", "600", "700", "800", "900", "1000"};
    private int progressBar = 0;
    private ProgressBar circleBar;
    private String value;

    /**
     * Date stuff testing
     */
    // Localdate object for retrieving the current date
    LocalDate currentDate = LocalDate.now();

    // this represents the day that the app gets from the latest water object
    public Calendar testCal = new Calendar.Builder().setCalendarType("iso8601").setDate(2021,5,2).build();

    // Integer which returns the latest water object's day as a number (1-31)
    public int latestDay() {
        return testCal.get(Calendar.DAY_OF_MONTH); // this is to be changed to a value retrieved from the water object
    }

    // this is the current date refreshed every time the app is laoded
    public Calendar currentCal = new Calendar.Builder().setCalendarType("iso8601").setDate(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth()).build();

    // Integer which returns the current day number (1-31)
    public int currentDay() {
        return currentCal.get(Calendar.DAY_OF_MONTH);
    }

    private String TAG = "WaterLog"; // easy to use tag for logging in Logcat

    /**
     * THIS CODE IS TO BE CHANGED WHEN THE PERSON-WATER-OBJECT BRANCH IS MERGED!!! For now it just shows the target which is set in the target field in shared preferences
     */
    public int getTargetAsMl() {
        //float amountGoal = Float.parseFloat(MainActivity.getTarget()) * 1000; // litres to millilitres
        //int amountGoalInt = Math.round(amountGoal); // round it so that the decimal point leaves
        //return amountGoalInt; // set the text as the goal in millilitres
        return 1234;
    }

    /**
     * Methods and values
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        textAmountGoal = main.findViewById(R.id.textAmountGoal); // get the textivew for the field to use
        textAmountGoal.setText("0 / " + getTargetAsMl() + " ml"); // set the text as the goal in millilitres using the function defined above
        textProgressBar = main.findViewById(R.id.textProgressBar);
        circleBar = main.findViewById(R.id.progress_bar);
        DecimalFormat decimal = new DecimalFormat("#.#");
        /**
         * Button to add amount of drink to store
         */
        addDrink = main.findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Adds drink amount to store
                //double helpValue = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() + Double.parseDouble(value));
                textAmountGoal.setText(value + " / " + getTargetAsMl() + " ml");
                int target = Integer.valueOf(getTargetAsMl());
                int onToGoal = Integer.valueOf(value);
                double prosent = (1.0 * onToGoal / target) * 100;
                int circleProsent = (int) Math.round(prosent);
                textProgressBar.setText(decimal.format(prosent) + " %");
                circleBar.setProgress(circleProsent);
                //DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).addingWater(onToGoal);
            }
        });
        /**
         * Numberpicker to take user value and storing that to store
         */
        amounPicker = main.findViewById(R.id.amountPicker);
        textAmount = main.findViewById(R.id.textAmount);

        /* Testing!! */

        Log.d(TAG, "onko ennen nykyhetkeä: " + Boolean.toString(currentCal.after(testCal)));

        Log.d(TAG, "viimeisin: " + Integer.toString(latestDay()));
        Log.d(TAG, "nykyinen: " + Integer.toString(currentDay()));

        if(latestDay() < currentDay()) {
            //create new water object
            Log.d(TAG, "create new water object");
        } else {
            //continue with the old water object
            Log.d(TAG, "continue using the existing water object");
        }



        /**
         * Numberpicker min, max and default values
         * @param setMinValue lowest index on drink-array
         * @param setMaxValue highest indes on drinks-array
         * @param setDisplayedValues shows drinks-array values on picker
         */
        amounPicker.setMinValue(0);
        amounPicker.setMaxValue(13);
        amounPicker.setDisplayedValues(drinks);
        /**
         * Numberpicker value picker
         */
        amounPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                //Amount of drink to store
                value = numberPicker.getDisplayedValues()[newVal];

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