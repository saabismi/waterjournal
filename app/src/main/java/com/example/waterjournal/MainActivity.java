package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Main activity that opens by default after profile creation.
 * Here user adds amount of drink to the store
 * User can remove resent amount if it was a mistake or amount was wrong
 * User can move in to the history activity or preference activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * Button and onClickListener to add amount of drink to store
         */
        Button addDrink = findViewById(R.id.buttonAddDrink);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Drink added!", Toast.LENGTH_SHORT).show();
            }
        });
        /**
         * Button and onClickListener to remove resent drink from store
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