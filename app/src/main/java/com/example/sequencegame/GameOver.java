package com.example.sequencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    Bundle details;
    TextView score;
    EditText name;
    String points;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        details = getIntent().getExtras();

        score = findViewById(R.id.gameOver);
        name = findViewById(R.id.editText);
        if (details != null) {
            points = details.getString("Score");
            score.setText(points);
        }
    }
    public void doDatabase(){
        DatabaseManager databaseManager = new DatabaseManager(this);

        // Open the database connection
        databaseManager.open();

        // Insert data
        long result = databaseManager.insert(String.valueOf(name.getText()),points );

        // Close the database connection
        databaseManager.close();
    }

    public void doHighScores(View view) {
        doDatabase();
        Intent highScores = new Intent(GameOver.this, HighScores.class);
        finish();
        startActivity(highScores);
    }

    public void doMenu(View view) {
        doDatabase();
        Intent menu = new Intent(GameOver.this, MainActivity.class);
        finish();
        startActivity(menu);
    }
}