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

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day
    private boolean getRegistered; // variable for getting the value of registration status key

    private int getWeight; //variable for getting the value of the weight key
    private static String getTarget; // variable for getting the value of the water target key

    //private TextView showWeight; // textview for weight
    //private TextView showTarget; // textview for target

    private String TAG = "WaterLog:"; // easy to use tag for logging

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        getRegistered = preferences.getBoolean(userRegistered, false);
        getWeight = preferences.getInt(userWeight, 0);
        getTarget = preferences.getString(userTarget, "undefined");
        getTarget();
        //showWeight = findViewById(R.id.showWeight);
        //showTarget = findViewById(R.id.showTarget);

        if (getRegistered != true) { // checking if the user has registered already
            Log.i(TAG, "User hasn't registered yet, let's go to Registration activity");
            Intent regIntent = new Intent(this, Registration.class); // create intent for going to Registration.java
            startActivity(regIntent); // start the activity Registration.java
        } else {
            Log.d(TAG, "User has already registered, continuing in MainActivity");
            //showWeight.setText(Integer.toString(getWeight) + " kg");
            //showTarget.setText(getTarget + " litres");
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

    public void reset(View v) {
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(userWeight, 75);
        editor.putString(userTarget, "x");
        editor.putBoolean(userRegistered, false);
        editor.commit();

        Intent regIntent = new Intent(this, Registration.class); // create intent for going to Registration.java
        startActivity(regIntent); // start the activity Registration.java
    }

    /**
     *
     * @return user's weight
     */
    public static String getTarget() {
        return getTarget;
    }

}