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
import androidx.navigation.Navigation;

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
 * Main fragment opens on default after user have set weight in registration activity.
 * @author Mikael Gustafsson, Andreas Mattson, Vilho Syvähuoko.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeFragment extends Fragment {

    /**
     * addDrink variable UI element will add water to WaterObject.
     */
    private Button addDrink;
    /**
     * removeResent variable UI element will remove latest added water from WaterObject.
     */
    private Button removeResent;
    /**
     * tips button is used for getting to tips fragment.
     */
    private Button tips;
    /**
     * amountPicker variable UI element will show drinks[] variable's list on a NumberPicker element.
     */
    private NumberPicker amountPicker;
    /**
     * textAmount variable UI element will show user current drinking amount.
     */
    private TextView textAmount;
    /**
     * textAmountGoal variable UI element will show user's minimum drinking amount per day.
     */
    private TextView textAmountGoal;
    /**
     * textProgressBar variable UI element will tell user's drinking progress.
     */
    private TextView textProgressBar;
    /**
     * drinks variable will be used with NumberPicker element.
     */
    public String drinks[] = {"40", "100", "200", "250", "300", "330", "400", "450", "500", "600", "700", "800", "900", "1000"};
    /**
     * circleBar variable UI element will show user's drinking progress in a circle with filling the circle with specific amount percent.
     */
    private ProgressBar circleBar;
    /**
     * value variable is used for determine which number has been picked from NumberPicker.
     */
    private String value;

    /**
     * waterJson variable will load water object information as a string from preferences.
     */
    public String watersJson;
    /**
     * waterGson variable will save WaterObject objects to mobile phones preferences.
     */
    public String watersGson;
    /**
     * gson variable will make it possible to save information and data to mobile phones preferences.
     */
    public Gson gson = new Gson();

    /**
     * waters variables is a list of WaterObject objects which user has created by using the app. It will
     * be used to fetch them from preferences and then add to singleton class or fetching them from singleton class
     * and then saving them to preferences.
     */
    public WaterList waters;

    /**
     * preferences variable is for being able to use mobile phone's preferences.
     */
    private SharedPreferences preferences;
    /**
     * USER_STORE variable will create preferences for storing information about the user, etc.
     */
    private final String USER_STORE = "UserStore";
    /**
     * waterList variable will create list object for storing the waters of the day.
     */
    private final String waterList = "waterList"; //

    /**
     * getWater will fetch information from WaterObject objects from preferences.
     */
    private String getWaters;

    /**
     * currentDate variable is for current date.
     */
    LocalDate currentDate = LocalDate.now();

    /**
     * this represents the day that the app gets from the latest water object.
     */
    public Calendar testCal = new Calendar.Builder().setCalendarType("iso8601").setDate(2021, 5, 2).build();

    /**
     * gets the value of the date of the latest water object.
     */
    private String SingletonDay;
    /**
     * int for the day extracted from the date.
     */
    private int SingletonDayInt;
    /**
     * initialise variable for getting the current date as a calendar object.
     */
    private Calendar currentCal;
    /**
     * currentDay variable is the current day in int form.
     */
    private int currentDay;

    /**
     * easy to use tag for logging in Logcat.
     */
    private String TAG = "WaterLog";


    /**
     * This function will return user's minimum amount of water user should drink at least per day from MainActivity class.
     * @return Will return user's minimum amount of water.
     */
    public int getTargetAsMl() {
        float amountGoal = Float.parseFloat(MainActivity.getTarget()); // litres to millilitres
        int amountGoalInt = Math.round(amountGoal); // round it so that the decimal point leaves
        return amountGoalInt; // set the text as the goal in millilitres
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param inflater           the LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          used to generate the LayoutParams of the view.
     * @param savedInstanceState this fragment is being re-constructed from a previous saved state as given here.
     * @return return the View for the fragment's UI, or null.
     */
    @RequiresApi(api = Build.VERSION_CODES.O)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_home, container, false);

        textAmountGoal = main.findViewById(R.id.textAmountGoal); // get the textivew for the field to use
        double drankValue = DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater();
        int drankValueInt = (int) drankValue;
        textAmountGoal.setText(Integer.toString(drankValueInt) + " / " + getTargetAsMl() + " ml"); // set the text as the goal in millilitres using the function defined above
        textProgressBar = main.findViewById(R.id.textProgressBar);
        circleBar = main.findViewById(R.id.progress_bar);
        DecimalFormat decimal = new DecimalFormat("#.#");

        //Get the date from the singleton
        SingletonDay = DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).toString();
        SingletonDayInt = SingletonDay.charAt(0);

        //Get the current date
        // this is the current date refreshed every time the app is loaded
        currentCal = new Calendar.Builder().setCalendarType("iso8601").setDate(currentDate.getYear(), currentDate.getMonthValue(), currentDate.getDayOfMonth()).build();
        // Integer which returns the current day number (1-31)
        currentDay = currentCal.get(Calendar.DAY_OF_MONTH);

        //Set the percent text in the middle of the progress bar
        int target = Integer.valueOf(getTargetAsMl());
        double percent = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater()) / target * 100;
        int circlePercent = (int) Math.round(percent);
        textProgressBar.setText(decimal.format(percent) + " %");
        circleBar.setProgress(circlePercent);

        preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

        getWaters = preferences.getString(waterList, "empty");

        waters = gson.fromJson(getWaters, WaterList.class);


        //Button to add amount of drink to store
        addDrink = main.findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            /**
             * This function will add water to WaterObject object once user has selected the amount which user wants to add and after
             * that clicked add button. After adding water this function will then update UI and show the amount which was added and sum it to
             * current amount. It will also update progress circle with current percent of daily intake.
             * @param view This parameter will tell which UI element was clicked and use this information to make this function work.
             */
            @Override
            public void onClick(View view) {
                // Adds drink amount to store
                double helpValue = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() + Double.parseDouble(value));
                int helpValueInt = (int) helpValue;

                //Will show on screen how much user has been drinking during current day.
                //target is the amount user should drink at least per day.
                //onToGoal is the amount which will be added to current water amount.
                //percent will be used to show right amount of percent on circlePercent variable.
                textAmountGoal.setText(helpValueInt + " / " + getTargetAsMl() + " ml");
                int target = Integer.valueOf(getTargetAsMl());
                int onToGoal = Integer.valueOf(value);
                double percent = (DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getAmountOfWater() + 1.0 * onToGoal) / target * 100;
                int circlePercent = (int) Math.round(percent);
                textProgressBar.setText(decimal.format(percent) + " %");

                //This if-sentence will inform user if daily goal is reached.
                if (circlePercent >= 100) {
                    Toast.makeText(getContext(), "Woohoo! You've achieved your daily goal!", Toast.LENGTH_SHORT).show();
                }

                circleBar.setProgress(circlePercent);
                DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).addingWater(onToGoal);

                //waters = gson.fromJson(getWaters, WaterList.class);
                //These variables will save added water information to preferences.
                String valueAsString = amountPicker.getDisplayedValues()[amountPicker.getValue()];
                waters.addWater(Integer.parseInt(valueAsString));
                watersGson = gson.toJson(waters);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(waterList, watersGson);
                editor.commit();
            }
        });

        //NumberPicker to take user value and storing that to store
        amountPicker = main.findViewById(R.id.amountPicker);
        textAmount = main.findViewById(R.id.textAmount);

        /* Testing!! */

        Log.d(TAG, "onko ennen nykyhetkeä: " + Boolean.toString(currentCal.after(testCal)));

        // Log.d(TAG, "viimeisin: " + Integer.toString(latestDay()));
        //Log.d(TAG, "nykyinen: " + Integer.toString(currentDay()));


        //This if-sentence will check if day has changed. If it has it will create new WaterObject object otherwise
        //it will use current WaterObject.
        if (SingletonDayInt < currentDay) {
            //create new water object
            waters = gson.fromJson(getWaters, WaterList.class);
            waters.reset();
            DailyDrinkingObject.getInstance().createWaterObject(getTargetAsMl());

            watersGson = gson.toJson(waters);
            SharedPreferences.Editor editor = preferences.edit(); // editor
            editor.putString(waterList, watersGson);
            editor.commit();
            Log.d(TAG, "create new water object");
        } else {
            //continue with the old water object
            Log.d(TAG, "continue using the existing water object");
        }

        /*
        NumberPicker min, max and default values
        setMinValue lowest index on drink-array
        setMaxValue highest indes on drinks-array
        setDisplayedValues shows drinks-array values on picker
         */
        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(13);
        amountPicker.setDisplayedValues(drinks);
        amountPicker.setValue(3);

        //NumberPicker value picker
        amountPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /**
             * This function will be used to pick amount of water user wants to add to user's daily intake.
             * @param numberPicker This parameter will be used to search values in drinks list.
             * @param oldVal This parameter is numberPickers earlier value.
             * @param newVal This parameter is numberPickers new value.
             */
            @Override
            public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
                //Amount of drink to store
                value = numberPicker.getDisplayedValues()[newVal];

            }
        });

        //Set the value of the "value" variable the preselected value on start
        value = amountPicker.getDisplayedValues()[amountPicker.getValue()];

        //Button to remove resent drink from store
        removeResent = main.findViewById(R.id.buttonRemoveResent);
        removeResent.setOnClickListener(new View.OnClickListener() {
            /**
             * This function is used to remove last added amount of water from user's daily intake. Function has if-sentence for not crashing if user decided
             * to click remove button before adding anything. Function will use singleton class and WaterObject object inside it to determine how to subtract two values.
             * After it has done subtraction it will update percent circle bar and text values.
             * @param view This parameter will tell which UI element was clicked.
             */
            @Override
            public void onClick(View view) {
                // Removes resent value from store
                //This if-sentence will make app not crash if user tries to remove value before adding anything.
                if (!DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getTimeAdded().isEmpty()) {
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

        //Button to go to the tips fragment.
        tips = main.findViewById(R.id.imageButtonTips);
        /* tipsBtn setOnClickListener. */
        tips.setOnClickListener(new View.OnClickListener() {
            /**
             * This function will go to the tips fragment once it is clicked.
             * @param view This parameter will tell from which activity are we going to next one.
             */
            @Override
            public void onClick(View view) {
                //OnClick to use navigation controller to go to the tips fragment.
                Navigation.findNavController(view).navigate(R.id.navigation_tips);
            }
        });
        return main;
    }

}