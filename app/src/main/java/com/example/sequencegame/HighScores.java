package com.example.sequencegame;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class HighScores extends AppCompatActivity {

    private ListView listViewHighScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        listViewHighScores = findViewById(R.id.listViewHighScores);

        // Fetch top 5 high scores from the database
        List<String> highScores = getTop5HighScores();

        // Display high scores in the ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, highScores);
        listViewHighScores.setAdapter(adapter);
    }

    private List<String> getTop5HighScores() {
        // Retrieve the top 5 high scores from the database
        // Example: You may use a cursor to query the database and retrieve the top 5 scores
        // Replace this with your actual database query

        DatabaseManager databaseManager = new DatabaseManager(this).open();
        Cursor cursor = databaseManager.getTop5Scores();

        List<String> topScores = databaseManager.convertCursorToList(cursor);

        cursor.close();
        databaseManager.close();

        return topScores;
    }

    public void showMenu(View view) {
        finish();
        Intent menu = new Intent(HighScores.this, MainActivity.class);
        startActivity(menu);
    }
}
