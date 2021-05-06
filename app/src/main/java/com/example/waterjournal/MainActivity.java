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
 */
public class MainActivity extends AppCompatActivity {

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

    //private TextView showWeight; // textView for weight
    //private TextView showTarget; // textView for target

    private String TAG = "WaterLog"; // easy to use tag for logging

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
        //showWeight = findViewById(R.id.showWeight);
        //showTarget = findViewById(R.id.showTarget);


        /**
         * First checking if the user is registered
         */

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

            /**
             * Checking if the user object has been created
             */
            if (!getCreated) {
                Log.d(TAG, "user object hasn't been created, gonna create it");
                user = new UserObject();
                user.changeWeight(getWeight); // update the user object with the new info
                userJson = gson.toJson(user); // user object to json form
                editor.putString(userObject, userJson); // set the value of the newly created user object to the storage
                editor.putBoolean(userCreated, true); // set the user object as created in the local storage

                // reset these values so that they won't be used again because they are now unnecessary
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
     *
     * @return user's weight
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