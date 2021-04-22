package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 *
 */
public class PreferencesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
    }

    /**
     * A metod to move in to the history activity
     * @param v
     */
    public void openHistory(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    /**
     * A metod to move in to the preference activity
     * @param v
     */
    public void openPreferences(View v){
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    /**
     * A metod to move in to the main activity
     * @param v
     */
    public void openHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}