package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class Registration extends AppCompatActivity {

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day

    private int getWeight; // get values from the preferences
    private String getTarget;

    private String TAG = "WaterLog:"; // easy to use tag for logging

    private RadioGroup radioSex; // radio group
    private RadioButton radioFemale; // radio button with value female
    private RadioButton radioMale; // radio button with value male
    private NumberPicker pickWeight; // number picker for choosing weight
    private TextView previewTarget; // textview for showing the preview for the water target
    private Button toMain; // button for going to the main activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        /* Set the values for the previously initialised variables */
        radioSex = findViewById(R.id.radiosSex);
        radioFemale = findViewById(R.id.radioFemale);
        radioMale = findViewById(R.id.radioMale);
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

        pickWeight.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker pickWeight, int oldVal, int newVal) {
                DecimalFormat dec = new DecimalFormat("#.0");
                Double target = newVal * 0.033;
                String visibleTarget = Double.toString(Double.valueOf(dec.format(target))) + " litres";
                previewTarget.setText(visibleTarget);
                editor.putString(userTarget, visibleTarget);
                editor.apply();
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
        editor.apply();
    }


    @Override
    protected void onStart() {
        super.onStart();

        /* set values for the views where the weight and target are displayed */
        pickWeight = findViewById(R.id.numPickWeight);
        previewTarget = findViewById(R.id.previewTarget);

        // get the value which is saved in the preferences, for weight and target
        preferences = getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

        /* set the values for the variables which gets the values from the preferences */
        getWeight = preferences.getInt(userWeight, 20);
        getTarget = preferences.getString(userTarget, null);

        /* Set the weight and calculated target to what they were previously set to */
        pickWeight.setValue(getWeight);
        previewTarget.setText(getTarget);
    }

}