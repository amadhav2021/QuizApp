package com.madhavamish.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity {

    Button final_answer, poll, eliminate, a, b, c, d;
    boolean poll_used, eliminate_used = false;

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

        poll = findViewById(R.id.poll);
        poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(poll_used){
                    Toast.makeText(getApplicationContext(), "You have already used the audience poll lifeline", Toast.LENGTH_SHORT).show();
                }
                else {
                    poll.setBackground(getResources().getDrawable(R.drawable.circle_gray, null));
                    poll.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    poll.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lose_option_foreground, 0, 0);
                    poll_used = true;
                }
            }
        });

        eliminate = findViewById(R.id.eliminate);
        eliminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(eliminate_used){
                    Toast.makeText(getApplicationContext(), "You have already used the 50:50 lifeline", Toast.LENGTH_SHORT).show();
                }
                else {
                    eliminate.setBackground(getResources().getDrawable(R.drawable.circle_gray, null));
                    eliminate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                    eliminate.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lose_option_foreground, 0, 0);
                    eliminate_used = true;
                }
            }
        });
    }

    public void answer_selected(View view) {
        a.setBackgroundColor(getResources().getColor(R.color.black));
        b.setBackgroundColor(getResources().getColor(R.color.black));
        c.setBackgroundColor(getResources().getColor(R.color.black));
        d.setBackgroundColor(getResources().getColor(R.color.black));
        view.setBackgroundColor(getResources().getColor(R.color.yellow));
        final_answer.setEnabled(true);
        final_answer.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

}