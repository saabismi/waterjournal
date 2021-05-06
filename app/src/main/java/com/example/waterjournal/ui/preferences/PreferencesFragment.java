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
import androidx.navigation.Navigation;

import com.example.waterjournal.MainActivity;
import com.example.waterjournal.R;
import com.example.waterjournal.UserObject;

import com.google.gson.Gson;

import java.text.DecimalFormat;

public class PreferencesFragment extends Fragment {

    public String userGson; // user converted to json
    public String userJson; // user object in json form
    public Gson gson = new Gson(); // Gson object

    private NumberPicker weightPicker, dailyPicker;
    private TextView textWeight, textAmount;
    private Button save, tips;

    private String TAG = "WaterLog";

    //public Gson gson = new Gson();

    public UserObject user;

    private final String USER_STORE = "UserStore"; // create preferences for storing information about the user, etc.
    private final String userWeight  = "userWeight"; // storage for storing the user weight
    private final String userTarget = "userTarget"; // target water amount per day
    private final String userObject = "userObject"; // location for the JSON formatted version of the user object
    private SharedPreferences preferences; // create sharedPreferences variable

    /* List of displayed values in the daily water number picker */
    public String targets[] = {"1.0", "1.1", "1.2","1.3","1.4","1.5","1.6","1.7","1.8","1.9","2.0","2.1","2.2","2.3", "2.4", "2.5", "2.6", "2.7", "2.8", "2.9", "3.0", "3.1", "3.2", "3.3", "3.4", "3.5", "3.6", "3.7", "3.8", "3.9", "4.0", "4.1", "4.2", "4.3", "4.4", "4.5", "4.6", "4.7", "4.8", "4.9", "5.0", "5.1", "5.2", "5.3", "5.4", "5.5"};

    private int getWeight; // get values from the preferences
    private String getTarget;
    private String getUser;

    /**
     * Calculate needed water amount, used when the user changes their weight but doesn't touch the target water amount
     * @param weight
     * @return
     */

    public String calcMinValuePref(int weight) {
        DecimalFormat dec = new DecimalFormat("#.0"); // use this to format to one decimal number

        double minimumAmount = weight * 0.033; // calculate default target ( = 75kg)
        return Double.toString(Double.valueOf(dec.format(minimumAmount)));
    }

    /**
     * Main view of the preferences fragment
     * Called to have the fragment instantiate its user interface view.
     * @param inflater the LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container used to generate the LayoutParams of the view.
     * @param savedInstanceState this fragment is being re-constructed from a previous saved state as given here.
     * @return return the View for the fragment's UI, or null.
     */

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View pref = inflater.inflate(R.layout.fragment_preferences, container, false);

        preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);

        /*person_stuff*/
        getUser = preferences.getString(userObject, "unexpected error"); // get the user object as a string from the storage
        user = gson.fromJson(getUser, UserObject.class); // transfer the user object from json to object

        SharedPreferences.Editor editor = preferences.edit();
        userJson = gson.toJson(user);


        /* new_object_branch
        getWeight = preferences.getInt(userWeight, 75); // get the value of the weight from the storage
        Log.d("paino", String.valueOf(getWeight));
        */
  
        getTarget = preferences.getString(userTarget, "undefined"); // get the value of the target from the storage

        String userJson = preferences.getString(userObject, "");
        user = gson.fromJson(userJson, UserObject.class);

        textWeight = pref.findViewById(R.id.textWeight);
        weightPicker = pref.findViewById(R.id.weightPicker);

        weightPicker.setMinValue(20);
        weightPicker.setMaxValue(200);
        weightPicker.setValue(user.getWeight());

      
        Log.d(TAG, "the user weight in the object is: " + user.getWeight());

        weightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Log.d(TAG, Integer.toString(weightPicker.getValue()));
            }
        });

        dailyPicker = pref.findViewById(R.id.drinkPicker);
        dailyPicker.setMinValue(0);
        dailyPicker.setMaxValue(45);
        dailyPicker.setDisplayedValues(targets);

        /**
         * Set values for the daily water amount picker
         */
        /* This should be a function but I didn't manage to do it, but at least it works... */
        String minimumAsString = Double.toString(user.getMinimumAmount());
        Log.d(TAG, "minimum as string: " + minimumAsString);

        int targetIndex = 0;
        while(true) {
            if(minimumAsString.equals(targets[targetIndex]) && targetIndex <= 45) {
                dailyPicker.setValue(targetIndex);
                break;
            } else {
                targetIndex++;
            }
        }

        textAmount = pref.findViewById(R.id.textDaily);

        Log.d(TAG, Integer.toString(targets.length));


        weightPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                user.changeWeight(i1);
                Log.d(TAG, "weight now :" + Integer.toString(user.getWeight()));

                int targetIndex2 = 0;
                while(true) {
                    if(calcMinValuePref(user.getWeight()).equals(targets[targetIndex2]) && targetIndex2 <= 45) {
                        dailyPicker.setValue(targetIndex2);
                        break;
                    } else {
                        targetIndex2++;
                    }
                }
            }
        });

        dailyPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                //textAmount.setText(" ml");
                String value = numberPicker.getDisplayedValues()[i1];
                Log.d(TAG, "target now: " + Double.toString(Double.parseDouble(value)));
                user.changeMinimumAmount(Double.parseDouble(value));
            }
        });

        save = pref.findViewById(R.id.buttonSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preferences = getActivity().getSharedPreferences(USER_STORE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                getUser = preferences.getString(userObject, "unexpected error"); // get the user object as a string from the storage
                String userJson = getUser; // transfer the user object from json to object
                user = gson.fromJson(userJson, UserObject.class);

                //double dailyValueDouble = Double.parseDouble(dailyPicker.getDisplayedValues()[dailyPicker.getValue()]);
                //user.changeMinimumAmount(dailyValueDouble);
                user.changeWeight(weightPicker.getValue());

                String targetAmount = dailyPicker.getDisplayedValues()[dailyPicker.getValue()];
                String targetString = Integer.toString(dailyPicker.getValue());

                double targetAmountDouble = Double.parseDouble(targetAmount);
                Log.d(TAG, "target saved as double: " + Double.toString(targetAmountDouble));
                user.changeMinimumAmount(targetAmountDouble);

                userGson = gson.toJson(user);

                editor.putInt(userWeight, weightPicker.getValue());
                editor.putString(userTarget, targetAmount);
                editor.putString(userObject, userGson);
                editor.commit();
                //Toast to screen
                Toast.makeText(getContext(),"Preferences saved",Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * Button to go to the tips fragment.
         */
        tips = pref.findViewById(R.id.imageButtonTips);
        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //OnClick to use navigation controller to go to the tips fragment.
                Navigation.findNavController(view).navigate(R.id.navigation_tips);
            }
        });

        return pref;
    }
}