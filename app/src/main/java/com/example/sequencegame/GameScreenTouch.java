package com.example.sequencegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Handler;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreenTouch extends AppCompatActivity {
    // Set colors for each quadrant
    Quadrant quadrant1;
    Quadrant quadrant2;
    Quadrant quadrant3;
    Quadrant quadrant4;
    SequenceGen sequenceGen;
    String[] sequence;
    TextView scoreText;
    Toast toast, toast2;
    int score, sequenceIndex, amount = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen_touch);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String o = extras.getString("Orientation");
            if ("Landscape".equals(o)) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
            } else {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
            }
        } else {
            // Handle the case where the intent extras are null
        }
        toast = Toast.makeText(GameScreenTouch.this, "Correct", Toast.LENGTH_SHORT);
        toast2 = Toast.makeText(GameScreenTouch.this, "Wrong", Toast.LENGTH_SHORT);
        scoreText = findViewById(R.id.gameScoreTouch);
        sequenceGen = new SequenceGen();
        quadrant1 = findViewById(R.id.quadrant1);
        quadrant1.setColor(Color.RED);

        quadrant2 = findViewById(R.id.quadrant2);
        quadrant2.setColor(Color.GREEN);

        quadrant3 = findViewById(R.id.quadrant3);
        quadrant3.setColor(Color.BLUE);

        quadrant4 = findViewById(R.id.quadrant4);
        quadrant4.setColor(Color.YELLOW);

        GenerateNewSequence();

        // Set click listeners for each quadrant
        quadrant1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sequenceIndex < sequence.length){
                    if(sequenceGen.CheckSequence(sequence, sequenceIndex, "red")){
                        sequenceIndex = sequenceIndex + 2;
                        toast.show();
                    }
                    else{
                        doGameOver();
                    }
                }else{
                    score = score + 2;
                    scoreText.setText(String.valueOf(score));
                    GenerateNewSequence();
                }
            }
        });

        quadrant2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sequenceIndex < sequence.length){
                    if(sequenceGen.CheckSequence(sequence, sequenceIndex, "green")){
                        sequenceIndex = sequenceIndex + 2;
                        toast.show();
                    }
                    else{
                        doGameOver();
                    }
                }else{
                    score = score + 2;
                    scoreText.setText(String.valueOf(score));
                    GenerateNewSequence();
                }
            }
        });

        quadrant3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sequenceIndex < sequence.length){
                    if(sequenceGen.CheckSequence(sequence, sequenceIndex, "blue")){
                        sequenceIndex = sequenceIndex + 2;
                        toast.show();
                    }
                    else{
                        doGameOver();
                    }
                }else{
                    score = score + 2;
                    scoreText.setText(String.valueOf(score));
                    GenerateNewSequence();
                }
            }
        });

        quadrant4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(sequenceIndex < sequence.length){
                    if(sequenceGen.CheckSequence(sequence, sequenceIndex, "yellow")){
                        sequenceIndex = sequenceIndex + 2;
                        toast.show();
                    }
                    else{
                        doGameOver();
                    }
                }else{
                    score = score + 2;
                    scoreText.setText(String.valueOf(score));
                    GenerateNewSequence();
                }
            }
        });
    }

    private void doGameOver() {
        toast2.show();
        Intent go = new Intent(GameScreenTouch.this,GameOver.class);
        String points = String.valueOf(score);
        go.putExtra("Score", points);
        finish();
        startActivity(go);
    }

    private void GenerateNewSequence() {
        amount ++;
        sequenceIndex = 0;
        sequence = sequenceGen.GenerateSequence(amount);
        FlashColors();
    }
    private void FlashColors() {
        final Handler handler = new Handler();
        int currentIndex = 0;
        quadrant1.setColor(Color.WHITE);
        quadrant2.setColor(Color.WHITE);
        quadrant3.setColor(Color.WHITE);
        quadrant4.setColor(Color.WHITE);
        for (int i = 0; i < sequence.length; i++) {
            currentIndex = i;
            switch (sequence[i]) {
                case "red":
                    delay(handler, ((currentIndex + 1) *500), "red");
                    break;
                case "blue":
                    delay(handler, ((currentIndex + 1) *500), "blue");
                    break;
                case "green":
                    delay(handler, ((currentIndex + 1) *500), "green");
                    break;
                case "yellow":
                    delay(handler, ((currentIndex + 1) *500), "yellow");
                    break;
                case "white":
                    delay(handler, ((currentIndex + 1) *500), "white");
            }
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                quadrant1.setColor(Color.RED);
                quadrant2.setColor(Color.GREEN);
                quadrant3.setColor(Color.BLUE);
                quadrant4.setColor(Color.YELLOW);
            }
        }, (currentIndex + 1) * 500);
    }
    private void delay(Handler handler, int time, String color){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Revert to the original color after one second
                switch (color){
                    case "red":
                        quadrant2.setColor(Color.RED);
                        break;
                    case "green":
                        quadrant2.setColor(Color.GREEN);
                        break;
                    case "blue":
                        quadrant2.setColor(Color.BLUE);
                        break;
                    case "yellow":
                        quadrant2.setColor(Color.YELLOW);
                        break;
                    case "white":
                        quadrant2.setColor(Color.WHITE);
                        break;
                }
            }
        }, (time));
    }
}