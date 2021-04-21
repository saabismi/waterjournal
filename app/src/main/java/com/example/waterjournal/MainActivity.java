package com.example.waterjournal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Buttons from main_layout
        Button addDrink = findViewById(R.id.buttonAddDrink);
        Button history = findViewById(R.id.buttonHistory);
        Button home = findViewById(R.id.buttonHome);
        Button removeResent = findViewById(R.id.buttonremoveResent);
        Button preferences = findViewById(R.id.buttonPreferences);

        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Drink added!", Toast.LENGTH_SHORT).show();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Back to the future!", Toast.LENGTH_SHORT).show();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"You are!", Toast.LENGTH_SHORT).show();
            }
        });
        removeResent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"Remove move move!", Toast.LENGTH_SHORT).show();
            }
        });
        preferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"No can do!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}