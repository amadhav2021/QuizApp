package com.madhavamish.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void shift_activity(View view){
        System.out.println("PLAY button pressed!");
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
    }
}
