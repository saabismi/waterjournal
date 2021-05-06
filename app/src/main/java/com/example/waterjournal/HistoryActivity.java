package com.example.waterjournal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Drink history activity
 * User can check daily drink amount
 * User can move in to the main activity or preference activity
 */
public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    public static final String EXTRA = "ListPosition";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        this.listView = findViewById(R.id.listViewForDays);
        this.listView.setAdapter(new ArrayAdapter<WaterObject>(this, android.R.layout.simple_list_item_1, DailyDrinkingObject.getInstance().getDailyWaterList()));
        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent specificDayView = new Intent(HistoryActivity.this, SpecificDayView.class);
                specificDayView.putExtra(EXTRA, position);
                startActivity(specificDayView);
            }
        });
    }

}