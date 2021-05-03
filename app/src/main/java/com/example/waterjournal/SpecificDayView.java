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

public class SpecificDayView extends AppCompatActivity {

    private TextView informationAboutUser;
    private TextView informationAboutWater;
    private ListView listOfDrinkingTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_specific_day_view);

        Bundle bundle = getIntent().getExtras();
        int list_position = bundle.getInt(HistoryFragment.EXTRA, 0);
        this.informationAboutUser = findViewById(R.id.informationAboutUser);
        this.informationAboutUser.setText(DailyDrinkingObject.getInstance().getSpecificWaterObject(list_position).getInformation());
        this.informationAboutWater = findViewById(R.id.informationAboutWater);
        this.informationAboutWater.setText(DailyDrinkingObject.getInstance().getSpecificWaterObject(list_position).getWaterInformation());

        this.listOfDrinkingTimes = findViewById(R.id.listOfDrinkingTimes);
        this.listOfDrinkingTimes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getSpecificDay(list_position)));
    }
}