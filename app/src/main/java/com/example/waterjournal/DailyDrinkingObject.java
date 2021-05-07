package com.example.waterjournal;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * This singleton class will add each day water object to an ArrayList which will keep them in memory.
 * This will give user a chance to check his daily or monthly water usage and compare them.
 * @author Andreas Mattson, Vilho Syvähuoko.
 */
public class DailyDrinkingObject {

    //Creating variables for class.
    /**
     * Gson variable
     */
    public Gson gson = new Gson();

    /**
     * Get application context from the main activity
     */
    Context applicationContext = MainActivity.getContextOfApplication();

    /**
     * USER_STORE will create a preference for user information.
     */
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    /**
     * userObject is a variable for making a location for JSON formatted version of user object.
     */
    private final String userObject = "userObject"; // location for the JSON formatted version of the user object
    /**
     * preference variable is a variable which let us use mobile phones preferences.
     */
    private SharedPreferences preferences = applicationContext.getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

    /**
     * getUser variable will get the user object from preferences as a string from memory storage
     */
    private String getUser = preferences.getString(userObject, "unexpected error"); // get the user object as a string from the storage
    /**
     * user variable is used to make convert user object from string to object.
     */
    public UserObject user = gson.fromJson(getUser, UserObject.class); // transfer the user object from json to object
    /**
     * minWater variable will get minimum amount of water.
     */
    private double minWater = user.getMinimumAmount();

    /**
     * dailyWater variable will save each WaterObject object to it's memory. Users will be able to use them.
     */
    private ArrayList<WaterObject> dailyWater;
    /**
     * ourInstance will able user to use DailyDrinkingObject in different activities without needing to create new DailyDrinkingObject each time.
     */
    private static final DailyDrinkingObject ourInstance = new DailyDrinkingObject();

    /**
     * This constructor will define new ArrayList for singleton class to use.
     */
    private DailyDrinkingObject() {
        this.dailyWater = new ArrayList<>();
        this.dailyWater.add(new WaterObject(this.minWater));
    }

    /**
     * This function will return singleton instance. User will be able to use this singleton classes mechanics
     * in different activities at the same time without needing to create new object every time.
     * @return This return will return instance of this singleton class to user.
     */
    public static DailyDrinkingObject getInstance() {
        return ourInstance;
    }

    /**
     * This function will add users earlier WaterObject objects to the dailyWater ArrayList when user opens Water Journal app. Later this list
     * will be used to save information to phones preferences.
     * @param earlierWaterObject This parameter is an WaterObject object which will be added to dailyWater ArrayList.
     */
    public void addEarlierWaterObjects(WaterObject earlierWaterObject) {
        this.dailyWater.add(earlierWaterObject);
    }

    /**
     * This function will return dailyWater ArrayList. It can be used in different ways for example in listView or saving information to
     * preferences. User will be able to check specific days and get information from them.
     * @return Will return an ArrayList<WaterObject> for user when called.
     */
    public ArrayList<WaterObject>getDailyWaterList() {
        return this.dailyWater;
    }

    /**
     * This function will return specific WaterObject objects water list. The list will show users water consumption
     * during that day.
     * @param listPosition Will use this given int number to search for a specific id in dailyWater list.
     * @return Will return specific WaterObject objects water consumption list which is defined in listPosition id.
     */
    public ArrayList<String> getSpecificDay(int listPosition) {
        return this.dailyWater.get(listPosition).getDrinkAdded();
    }

    /**
     * This function will return WaterObject object for a specific day. User will be able to use this in different situations for example
     * use it to add water to the object or to remove water from the object.
     * @param listPosition Will use this given int number to search for a specific id in dailyWater list.
     * @return Will return WaterObject object for a specific day.
     */
    public WaterObject getSpecificWaterObject(int listPosition) {
        return this.dailyWater.get(listPosition);
    }

    /**
     * This function will create new WaterObject object for user to use. It will do it when new days begins. After this
     * app will use this newly created object and save earlier object to memory.
     * @param minimumAmount This parameter will be used to define minimum amount of water user should drink for object.
     */
    public void createWaterObject(double minimumAmount) {
        this.dailyWater.add(new WaterObject(minimumAmount));
    }
}