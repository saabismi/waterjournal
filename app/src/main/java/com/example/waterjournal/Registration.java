package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Registration extends AppCompatActivity {

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userRegistered = "userRegistered"; // storage for sharing the information about whether the user is registered already or not
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userSex = "userSex"; // storage for storing the user's sex
    private final String userTarget = "userTarget"; // target water amount per day

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

        pickWeight.setMaxValue(200);
        pickWeight.setMinValue(20);
        pickWeight.setValue(75);

    }
}