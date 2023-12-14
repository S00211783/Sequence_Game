package com.example.sequencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
    public boolean s = false; // Initialize with a default value
    public boolean l = false; // Initialize with a default value
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doGame(View view) {
            Intent gameWithTouch = new Intent(MainActivity.this, GameScreenTouch.class);
            if (l) {
                gameWithTouch.putExtra("Orientation", "Landscape");
            } else {
                gameWithTouch.putExtra("Orientation", "Portrait");
            }
            finish();
            startActivity(gameWithTouch);
        }

    public void doHighScores(View view) {
        finish();
        Intent highScores = new Intent(MainActivity.this, HighScores.class);
        startActivity(highScores);
    }

    public void handleSwitch(View view) {
        // Toggle the value of 's'
        s = !s;
    }

    public void handleLandscape(View view) {
        // Toggle the value of 'l'
        l = !l;
    }
}
