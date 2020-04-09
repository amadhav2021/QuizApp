package com.madhavamish.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScreen extends AppCompatActivity {

    Button final_answer;
    TextView a, b, c, d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        a = findViewById(R.id.choiceA);
        b = findViewById(R.id.choiceB);
        c = findViewById(R.id.choiceC);
        d = findViewById(R.id.choiceD);

        final_answer = findViewById(R.id.final_answer);
        final_answer.setEnabled(false);

        final_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setBackgroundColor(getResources().getColor(R.color.green));
                b.setBackgroundColor(getResources().getColor(R.color.green));
                c.setBackgroundColor(getResources().getColor(R.color.green));
                d.setBackgroundColor(getResources().getColor(R.color.green));
            }
        });
    }

    public void answer_selected(View view) {
        view.setBackgroundColor(getResources().getColor(R.color.yellow));
        final_answer.setEnabled(true);
    }

}
