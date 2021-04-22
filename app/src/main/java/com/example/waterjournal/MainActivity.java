package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         *
         */
        Button addDrink = findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Drink added!", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         *
         */
        Button removeResent = findViewById(R.id.buttonRemoveResent);
        removeResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Remove move move!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     *
     * @param v
     */
    public void openHistory(View v){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param v
     */
    public void openPreferences(View v){
        Intent intent = new Intent(this, PreferencesActivity.class);
        startActivity(intent);
    }

    /**
     *
     * @param v
     */
    public void openHome(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}