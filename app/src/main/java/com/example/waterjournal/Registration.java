package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

/**
 * This class will create activity which will guide user to make his first PersonObject object for the app.
 * This object will be later used to create WaterObject object which will track user's drinking on specific days.
 */
public class Registration extends AppCompatActivity {

    // variables for intent extras
    public static final String WEIGHT = "666";
    public static final String TARGET = "undefined";

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day

    private int getWeight; // get values from the preferences
    private String getTarget;

    private String TAG = "WaterLog"; // easy to use tag for logging

    private NumberPicker pickWeight; // number picker for choosing weight
    private TextView previewTarget; // textview for showing the preview for the water target
    private Button toMain; // button for going to the main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Set the values for the previously initialised variables */
        pickWeight = findViewById(R.id.numPickWeight);
        previewTarget = findViewById(R.id.previewTarget);
        toMain = findViewById(R.id.toMainButton);

        /* Set up sharedpreferences */
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        /* Set up the numberpicker for the weight */
        pickWeight.setMaxValue(200);
        pickWeight.setMinValue(20);
        pickWeight.setValue(75);

        DecimalFormat dec = new DecimalFormat("#.0"); // use this to format to one decimal number

        double defaultTarget = 75 * 0.033; // calculate default target ( = 75kg)
        String defaultStr = Double.toString(Double.valueOf(dec.format(defaultTarget)));
        previewTarget.setText(defaultStr + " litres");
        editor.putString(userTarget, defaultStr);
        editor.commit();

        pickWeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker pickWeight, int oldVal, int newVal) {
                double target = newVal * 0.033;
                //String targetStr = Double.toString(target);
                String targetStr = Double.toString(Double.valueOf(dec.format(target)));
                previewTarget.setText(targetStr + " litres");
                editor.putString(userTarget, targetStr);
                editor.commit();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        /* Present the variables for the storage and the editor */
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        /* Save the weight when the app is paused */
        editor.putInt(userWeight, pickWeight.getValue());
        editor.commit();
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        /* set values for the views where the weight and target are displayed */
        pickWeight = findViewById(R.id.numPickWeight);
        previewTarget = findViewById(R.id.previewTarget);

        // get the value which is saved in the preferences, for weight and target
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

        /* set the values for the variables which gets the values from the preferences */
        getWeight = preferences.getInt(userWeight, 75);
        getTarget = preferences.getString(userTarget, "undefined");

        /* Set the weight and calculated target to what they were previously set to */
        pickWeight.setValue(getWeight);
        previewTarget.setText(getTarget + " litres");
    }

    public void goToMain(View v) {
        /* Create intent for returning to the main activity */
        Intent regedIntent = new Intent(this, MainActivity.class);

        /* Present the variables for the storage and the editor */
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(userRegistered, true);
        editor.commit();

        Log.d(TAG, "Registration complete, returning to main activity"); // for debugging purposes
        startActivity(regedIntent); // Go to the main activity
    }

}