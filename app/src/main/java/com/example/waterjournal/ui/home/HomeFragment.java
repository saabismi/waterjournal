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
import android.widget.Toast;

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
import com.example.waterjournal.WaterList;
import com.google.gson.Gson;

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
    private NumberPicker amountPicker;
    private TextView textAmount, textAmountGoal, textProgressBar;
    public String drinks[] = {"40", "100", "200", "250", "300", "330", "400", "450", "500", "600", "700", "800", "900", "1000"};
    private int progressBar = 0;
    private ProgressBar circleBar;
    private String value;


    public String watersJson;
    public String watersGson;
    public Gson gson = new Gson();

    public WaterList waters;

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String waterList = "waterList"; // list object for storing the waters of the day

    private String getWaters;

    /**
     * Date stuff testing
     */
    // Localdate object for retrieving the current date
    LocalDate currentDate = LocalDate.now();

    // this represents the day that the app gets from the latest water object
    public Calendar testCal = new Calendar.Builder().setCalendarType("iso8601").setDate(2021, 5, 2).build();

    // Integer which returns the latest water object's day as a number (1-31)
    public int latestDay() {
        return testCal.get(Calendar.DAY_OF_MONTH); // this is to be changed to a value retrieved from the water object
    }

    // this is the current date refreshed every time the app is loaded
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
        float amountGoal = Float.parseFloat(MainActivity.getTarget()); // litres to millilitres
        int amountGoalInt = Math.round(amountGoal); // round it so that the decimal point leaves
        return amountGoalInt; // set the text as the goal in millilitres
        //return 1234;
    }

    /**
     * Methods and values
     *
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        textAmountGoal = main.findViewById(R.id.textAmountGoal); // get the textivew for the field to use
        double drankValue = DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater();
        int drankValueInt = (int) drankValue;
        textAmountGoal.setText(Double.toString(drankValueInt) + " / " + getTargetAsMl() + " ml"); // set the text as the goal in millilitres using the function defined above
        textProgressBar = main.findViewById(R.id.textProgressBar);
        circleBar = main.findViewById(R.id.progress_bar);
        DecimalFormat decimal = new DecimalFormat("#.#");

        /**
         * Set the percent text in the middle of the progress bar
         */
        int target = Integer.valueOf(getTargetAsMl());
        double percent = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater()) / target * 100;
        int circlePercent = (int) Math.round(percent);
        textProgressBar.setText(decimal.format(percent) + " %");

        preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

        getWaters = preferences.getString(waterList, "empty");

        waters = gson.fromJson(getWaters, WaterList.class);


        /**
         * Button to add amount of drink to store
         */
        addDrink = main.findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Adds drink amount to store
                double helpValue = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() + Double.parseDouble(value));
                int helpValueInt = (int) helpValue;
                textAmountGoal.setText(helpValueInt + " / " + getTargetAsMl() + " ml");
                int target = Integer.valueOf(getTargetAsMl());
                int onToGoal = Integer.valueOf(value);
                double percent = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() + 1.0 * onToGoal) / target * 100;
                int circlePercent = (int) Math.round(percent);
                textProgressBar.setText(decimal.format(percent) + " %");

                if(circlePercent >= 100) {
                    Toast.makeText(getContext(),"Woohoo! You've achieved your daily goal!",Toast.LENGTH_SHORT).show();
                }

                circleBar.setProgress(circlePercent);
                DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).addingWater(onToGoal);

                //waters = gson.fromJson(getWaters, WaterList.class);
                String valueAsString = amountPicker.getDisplayedValues()[amountPicker.getValue()];
                waters.addWater(Integer.parseInt(valueAsString));
                watersGson = gson.toJson(waters);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(waterList, watersGson);
                editor.commit();
            }
        });

        /**
         * Numberpicker to take user value and storing that to store
         */
        amountPicker = main.findViewById(R.id.amountPicker);
        textAmount = main.findViewById(R.id.textAmount);

        /* Testing!! */

        Log.d(TAG, "onko ennen nykyhetkeä: " + Boolean.toString(currentCal.after(testCal)));

        Log.d(TAG, "viimeisin: " + Integer.toString(latestDay()));
        Log.d(TAG, "nykyinen: " + Integer.toString(currentDay()));

        if (latestDay() < currentDay()) {
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
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(13);
        amountPicker.setDisplayedValues(drinks);
        amountPicker.setValue(3);
        /**
         * Numberpicker value picker
         */
        amountPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                //Amount of drink to store
                value = numberPicker.getDisplayedValues()[newVal];

            }
        });

        /**
         * Set the value of the "value" variable the preselected value on start
         */
        value = amountPicker.getDisplayedValues()[amountPicker.getValue()];

        /**
         * Button to remove resent drink from store
         */
        removeResent = main.findViewById(R.id.buttonRemoveResent);
        removeResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Removes resent value from store
                if (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getTimeAdded().isEmpty()) {

                } else {
                    double helpValue = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() - DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).lastAddedDrink());
                    int helpValueInt = (int) helpValue;
                    DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).removingWater();
                    textAmountGoal.setText(helpValueInt + " / " + getTargetAsMl() + " ml");
                    int target = Integer.valueOf(getTargetAsMl());
                    double percent = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater()) / target * 100;
                    int circlePercent = (int) Math.round(percent);
                    textProgressBar.setText(decimal.format(percent) + " %");
                    circleBar.setProgress(circlePercent);

                    //waters = gson.fromJson(getWaters, WaterList.class);
                    waters.removeLatestwater();
                    watersGson = gson.toJson(waters);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(waterList, watersGson);
                    editor.commit();
                }
            }
        });

        return main;
    }

}