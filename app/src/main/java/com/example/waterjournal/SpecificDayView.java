package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.waterjournal.ui.history.HistoryFragment;

/**
 * This SpecificDayView function is for user to view user information regarding to drinking. It will show specific information about user
 * and how many times and how much user drank on specific day.
 */
public class SpecificDayView extends AppCompatActivity {

    //Creating private variables for the class.
    //informationAboutUser variable will get information about user using WaterObject object.
    //listOfDrinkingTimes list has list of user's drinks user took on the specific day.
    private TextView informationAboutUser;
    private ListView listOfDrinkingTimes;

    /**
     * This onCreate function will create SpecificDayView activity and show user's information on a textView UI element and show
     * specific day drinking information on a listView UI element.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_day_view);

        //Creating bundle variable for HistoryFragment intent EXTRA information and using it to get listView position.
        Bundle bundle = getIntent().getExtras();
        int list_position = bundle.getInt(HistoryFragment.EXTRA, 0);

        this.informationAboutUser = findViewById(R.id.informationAboutUser);
        //Adding specific day information to user information textView.
        this.informationAboutUser.setText(DailyDrinkingObject.getInstance().getSpecificWaterObject(list_position).getInformation());

        this.listOfDrinkingTimes = findViewById(R.id.listOfDrinkingTimes);
        //Adding specific day's drinking list to SpecificDayView listView UI element. It will tell how much user has drank and at what time.
        this.listOfDrinkingTimes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getSpecificDay(list_position)));
    }
}