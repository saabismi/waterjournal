package com.example.waterjournal.ui.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.waterjournal.MainActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.UserObject;

public class PreferencesFragment extends Fragment {

    private NumberPicker weightPicker, dailyPicker;
    private TextView textWeight, textAmount;
    private Button save;
    private String TAG = "WaterLog:";
    public Gson gson = new Gson();

    private SharedPreferences preferences; // create sharedpreferences variable
    private final String userObject = "userObject";
    public UserObject user;
    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day
    public String targets[] = {"1.0","1.2","1.3","1.4","1.5","1.6","1.7","1.8","1.9","2.0","2.1","2.2","2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "4.0", "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8", "4.9", "5.0", "5.1", "5.2", "5.3", "5.4", "5.5"};

    private int getWeight; // get values from the preferences
    private String getTarget;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View pref = inflater.inflate(R.layout.fragment_preferences, container, false);

        preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE); // set the preferences variable

        getWeight = preferences.getInt(userWeight, 75); // get the value of the weight from the storage
        Log.d("paino", String.valueOf(getWeight));
        getTarget = preferences.getString(userTarget, "undefined"); // get the value of the target from the storage

        String userJson = preferences.getString(userObject, "");
        user = gson.fromJson(userJson, UserObject.class);

        textWeight = pref.findViewById(R.id.textWeight);
        weightPicker = pref.findViewById(R.id.weightPicker);

        weightPicker.setMinValue(20);
        weightPicker.setMaxValue(200);
        weightPicker.setValue(user.getWeight());

        //textWeight.setText(" kg");
        //textAmount.setText(" ml");

        weightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //textWeight.setText(" kg");
                Log.d(TAG, Integer.toString(weightPicker.getValue()));
            }
        });



        textAmount = pref.findViewById(R.id.textDaily);
        dailyPicker = pref.findViewById(R.id.drinkPicker);


        dailyPicker.setMinValue(0);
        dailyPicker.setMaxValue(54);
        //dailyPicker.setValue(24);

        int targetIndex = 0;
        while(true) {
            if(getTarget.equals(targets[targetIndex]) && targetIndex <= 54) {
                dailyPicker.setValue(targetIndex);
                break;
            } else {
                targetIndex++;
            }

        }

        dailyPicker.setDisplayedValues(targets);

        dailyPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //textAmount.setText(" ml");
                String value = numberPicker.getDisplayedValues()[i1];
                Log.d(TAG, Integer.toString(dailyPicker.getValue()));
            }
        });

        save = pref.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                String targetAmount = dailyPicker.getDisplayedValues()[dailyPicker.getValue()];
                String targetString = Integer.toString(dailyPicker.getValue());
                editor.putInt(userWeight, weightPicker.getValue());
                editor.putString(userTarget, targetAmount);
                editor.commit();
                //Toast to screen
                Toast.makeText(getContext(),"Preferences saved",Toast.LENGTH_SHORT).show();
            }
        });

        return pref;
    }
}