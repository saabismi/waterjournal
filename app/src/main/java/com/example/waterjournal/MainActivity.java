package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day
    private boolean getRegistered; // variable for getting the value of registration status key
    private int getWeight; //variable for getting the value of the weight key
    private String getSex; // varbiale for getting the value of the sex key
    private String getTarget; // variable for getting the value of the water target key

    private String TAG = "WaterLog:"; // easy to use tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        getRegistered = preferences.getBoolean(userRegistered, false);
        getWeight = preferences.getInt(userWeight, 0);
        getTarget = preferences.getString(userTarget, null);

        if (getRegistered != true) { // checking if the user has registered already
            Log.i(TAG, "User hasn't registered yet, let's go to Registration activity");
            Intent regIntent = new Intent(this, Registration.class); // create intent for going to Registration.java
            startActivity(regIntent); // start the activity Registration.java
        } else {
            Log.i(TAG, "User has already registered, continuing in MainActivity");
        }

    }


}