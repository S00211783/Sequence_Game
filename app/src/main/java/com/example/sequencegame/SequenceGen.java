package com.example.sequencegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SequenceGen {
    private String[] colors = {"red", "blue", "green", "yellow"};
    private Random random = new Random();

    public String[] GenerateSequence(int sequenceLength) {
        List<String> sequence = new ArrayList<>();
        for (int i = 0; i < sequenceLength; i++) {
            int randomIndex = random.nextInt(colors.length);
            String randomColor = colors[randomIndex];
            sequence.add(randomColor);
            sequence.add("white");
        }
        return sequence.toArray(new String[0]);
    }
    public boolean CheckSequence(String[] sequence,int i, String color){
        if(sequence[i].equals(color)){
            return true;
        }
        else{
            return false;
        }
    }
}
