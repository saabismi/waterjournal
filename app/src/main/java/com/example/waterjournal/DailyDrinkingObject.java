package com.example.waterjournal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This singleton class will add each day water object to an ArrayList which will keep them in memory.
 * This will give user a chance to check his daily or monthly water usage and compare them.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class DailyDrinkingObject {

    /**
     * Gson variable
     */
    public Gson gson = new Gson();

    /**
     * Get application context from the main activity
     */
    Context applicationContext = MainActivity.getContextOfApplication();

    /**
     * Set variables for the sharedpreferences
     */
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userObject = "userObject"; // location for the JSON formatted version of the user object
    private SharedPreferences preferences = applicationContext.getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);


    private String TAG = "WaterLog"; // easy to use tag for logging in Logcat


    /**
     * Get the minimum amount from the user object
     */
    private String getUser = preferences.getString(userObject, "unexpected error"); // get the user object as a string from the storage
    public UserObject user = gson.fromJson(getUser, UserObject.class); // transfer the user object from json to objec
    private double minWater = user.getMinimumAmount();


    //Creating variables for class.
    private ArrayList<WaterObject> dailyWater;
    private static final DailyDrinkingObject ourInstance = new DailyDrinkingObject();


    /**
     * This constructor will define new ArrayList for singleton class to use.
     */
    private DailyDrinkingObject() {
        this.dailyWater = new ArrayList<>();
        this.dailyWater.add(new WaterObject(this.minWater));
    }

    public static DailyDrinkingObject getInstance() {
        return ourInstance;
    }

    /**
     * This function will add yesterdays water amount to the ArrayList.
     * @param dailyAmount This parameter will add new WaterObject to private dailyWater list.
     */
    public void addDailyWater(WaterObject dailyAmount) {
        this.dailyWater.add(dailyAmount);
    }

    /**
     * This function will return ArrayList. It can be used in different ways for example in listView. User
     * will be able to check specific days and get information from them.
     * @return Will return an ArrayList<WaterObject> for anyone who is asking for it.
     */
    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    /**
     * This function will return specific day.
     * @param listPosition Will use this given int number to search for a specific id in dailyWater list.
     * @return Will return the WaterObject which is in this specificDay id.
     */
    public ArrayList<String> getSpecificDay(int listPosition) {
        return this.dailyWater.get(listPosition).getDrinkAdded();
    }

    public WaterObject getSpecificWaterObject(int listPosition) {
        return this.dailyWater.get(listPosition);
    }

    public void createWaterObject(double minimumAmount) {
        this.dailyWater.add(new WaterObject(minimumAmount));
    }
}