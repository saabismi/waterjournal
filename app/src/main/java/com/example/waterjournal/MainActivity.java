package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * Main activity that opens by default after profile creation.
 * Here user adds amount of drink to the store
 * User can remove resent amount if it was a mistake or amount was wrong
 * User can move in to the history activity or preference activity
 * @author Mikael Gustafsson, Andeass Mattson, Vilho Syvähuoko.
 */
public class MainActivity extends AppCompatActivity {

    //Creating public and private variables for MainActivity class.
    //userJson variable is for getting user's object information from gson files or add user's objetc information to gson files.
    //gson variable is for being able to use gson in MainActivity for saving and fetching information for code.
    //user variable is for user object which will be created using UserObject class. It can be also defined by fetching information from gson UserObject files.

    public static Context contextOfApplication;

    public String userGson;
    public String userJson;
    public String watersJson;
    public String watersGson;
    public Gson gson = new Gson();

    public UserObject user;
    public WaterList waters;

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userCreated = "userCreated"; // key for checking if the user object has already been created or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day
    private final String userObject = "userObject"; // location for the JSON formatted version of the user object
    private final String waterList = "waterList"; // list object for storing the waters of the day
    private boolean getRegistered; // variable for getting the value of registration status key
    private boolean getCreated; // get the value of the person object creation status key

    private static int getWeight; //variable for getting the value of the weight key
    private static String getTarget; // variable for getting the value of the water target key
    private String getUser;
    private String getWaters;

    private String TAG = "WaterLog"; // easy to use tag for logging

    /**
     * This onCreate function will create MainActivity class view. It will also open in different ways depending if user has already defined settings.
     * If user hasn't defined setting it will Start Registration class which will guide user to make first account. But if user has already created an account
     * it will start MainActivity and HomeFragment activities.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Variable for context of application
         */
        contextOfApplication = getApplicationContext();


        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        getRegistered = preferences.getBoolean(userRegistered, false);
        getCreated = preferences.getBoolean(userCreated, false);
        getWeight = preferences.getInt(userWeight, 0);
        getTarget = preferences.getString(userTarget, "undefined");
        //getTarget();

        SharedPreferences.Editor editor = preferences.edit(); // editor

        getUser = preferences.getString(userObject, "unexpected error"); // get the user object as a string from the storage

        //This if-sentence will check if user has registered to the app. If user hasn't registered it will automatically open Registration class activity
        //for user. If user has already registered it will use already created UserObject.

        if (!getRegistered) { // checking if the user has registered already
            user = new UserObject();
            userJson = gson.toJson(user);
            editor.putString(userObject, userJson); // set the value of the newly created user object to the storage
            editor.commit();
            Log.d(TAG, "User hasn't registered yet, let's go to Registration activity");
            Intent regIntent = new Intent(this, Registration.class); // create intent for going to Registration.java
            startActivity(regIntent); // start the activity Registration.java
        } else {
            Log.d(TAG, "User has already registered, continuing in MainActivity");

            //This if-sentence will check if WaterObject class object has been created. If it hasn't been created it will create one using WaterObject class.
            //If there has been created one it will use last used WaterObject object.
            if (!getCreated) {
                Log.d(TAG, "user object hasn't been created, gonna create it");
                user = new UserObject();
                user.changeWeight(getWeight); // update the user object with the new info
                userJson = gson.toJson(user); // user object to json form
                editor.putString(userObject, userJson); // set the value of the newly created user object to the storage
                editor.putBoolean(userCreated, true); // set the user object as created in the local storage

                //New WaterObject object will be created by using UserObject classes minimum amount of water.
                //After that it will be saved to mobile phones preferences.
                DailyDrinkingObject.getInstance().createWaterObject(user.getMinimumAmount());
                editor.putInt(userWeight, user.getWeight());
                editor.putString(userTarget, String.valueOf(DailyDrinkingObject.getInstance().getSpecificWaterObject(0).getMinimumWater()));

                // commit the changes
                editor.commit();
            } else {
                Log.d(TAG, "user object already exists, no need to create it");
                user = gson.fromJson(getUser, UserObject.class); // transfer the user object from json to objec
              
                Log.d("paino", String.valueOf(user.getWeight()));
                //userTarget = String.valueOf(user.getMinimumAmount());
            }
        }

        getWaters = preferences.getString(waterList, "empty");

        if(getWaters != "empty") {
            Log.d(TAG, "waterlist object exists, getting the old one from storage");
            waters = gson.fromJson(getWaters, WaterList.class);

            if(waters.getSize() > 0) {
                for(int i = 0; i < waters.getSize(); i++) {
                    double addingAmount = waters.getValue(i);
                    DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).addingWater(addingAmount);
                }
            }
        } else {
            Log.d(TAG, "waterlist object not existing yet, creating new one");
            waters = new WaterList();

            watersGson = gson.toJson(waters);
            editor.putString(waterList, watersGson);
            editor.commit();
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_history, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

    public static Context getContextOfApplication(){
        return contextOfApplication;
    }


    /**
     * This function will return user's minimum amount of water user should drink daily.
     * @return Will return minimum amount of water.
     */
    public static String getTarget() {
        if (DailyDrinkingObject.getInstance().getDailyWaterList().size() == 0) {
            return "0";
        } else {
            return String.valueOf(DailyDrinkingObject.getInstance().getSpecificWaterObject(DailyDrinkingObject.getInstance().getDailyWaterList().size() - 1).getMinimumWater());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        // tähän singletonin tallennus jos ehtii

    }

}