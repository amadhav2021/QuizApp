package com.madhavamish.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class GameScreen extends AppCompatActivity implements PollDialog.PollDialogListener, ElimDialog.ElimDialogListener, GameOverDialog.GameOverListener, VictoryDialog.VictoryDialogListener {

    Button final_answer, poll, eliminate, first_elim, second_elim, a, b, c, d;
    TextView curr_question, curr_prize;
    String[] myQuestions, myOptions, myAnswers;
    String chosen;
    int level = 0;
    boolean poll_used, eliminate_used = false;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        a = findViewById(R.id.choiceA);
        b = findViewById(R.id.choiceB);
        c = findViewById(R.id.choiceC);
        d = findViewById(R.id.choiceD);

        first_elim = null;
        second_elim = null;

        curr_question = findViewById(R.id.question);
        curr_prize = findViewById(R.id.money);
        myQuestions = getResources().getStringArray(R.array.questions);
        myOptions = getResources().getStringArray(R.array.options);
        myAnswers = getResources().getStringArray(R.array.answers);

        curr_question.setText(myQuestions[level]);
        a.setText(myOptions[level * 4]);
        b.setText(myOptions[level * 4 + 1]);
        c.setText(myOptions[level * 4 + 2]);
        d.setText(myOptions[level * 4 + 3]);

        preferences = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.clear().apply();

        poll_used = preferences.getBoolean("pollUsed", false);
        eliminate_used = preferences.getBoolean("elimUsed", false);

        final_answer = findViewById(R.id.final_answer);
        final_answer.setEnabled(false);

        final_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_elim = null;
                second_elim = null;

                switch (myAnswers[level]) {
                    case "a":
                        a.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case "b":
                        b.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    case "c":
                        c.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                    default:
                        d.setBackgroundColor(getResources().getColor(R.color.green));
                        break;
                }
                if (!chosen.equals(myAnswers[level])) {
                    switch (chosen) {
                        case "a":
                            a.setBackgroundColor(getResources().getColor(R.color.red));
                            break;
                        case "b":
                            b.setBackgroundColor(getResources().getColor(R.color.red));
                            break;
                        case "c":
                            c.setBackgroundColor(getResources().getColor(R.color.red));
                            break;
                        default:
                            d.setBackgroundColor(getResources().getColor(R.color.red));
                            break;
                    }

                    GameOverDialog game_over = new GameOverDialog();
                    game_over.setCancelable(false);
                    game_over.show(getSupportFragmentManager(), "game over");

                } else {
                    level++;
                    if(level == myAnswers.length){
                        VictoryDialog victoryDialog = new VictoryDialog();
                        victoryDialog.show(getSupportFragmentManager(), "victory");
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.correct, Toast.LENGTH_SHORT).show();
                        String prize = "$" + level * 1000;
                        curr_prize.setText(prize);
                        curr_question.setText(myQuestions[level]);
                        a.setText(myOptions[level * 4]);
                        b.setText(myOptions[level * 4 + 1]);
                        c.setText(myOptions[level * 4 + 2]);
                        d.setText(myOptions[level * 4 + 3]);

                        a.setBackgroundColor(getResources().getColor(R.color.black));
                        b.setBackgroundColor(getResources().getColor(R.color.black));
                        c.setBackgroundColor(getResources().getColor(R.color.black));
                        d.setBackgroundColor(getResources().getColor(R.color.black));

                        final_answer.setEnabled(false);
                        final_answer.setBackgroundColor(getResources().getColor(R.color.gray));
                    }
                }
            }
        });

        poll = findViewById(R.id.poll);
        if (poll_used) {
            pollUsed();
        }
        poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (poll_used) {
                    Toast.makeText(getApplicationContext(), "You have already used the audience poll lifeline", Toast.LENGTH_SHORT).show();
                } else {
                    pollDialog();
                }
            }
        });

        eliminate = findViewById(R.id.eliminate);
        if (eliminate_used) {
            elimUsed();
        }
        eliminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (eliminate_used) {
                    Toast.makeText(getApplicationContext(), "You have already used the 50:50 lifeline", Toast.LENGTH_SHORT).show();
                } else {
                    elimDialog();
                }
            }
        });
    }

    private void pollDialog() {
        PollDialog pollDialog = new PollDialog();
        pollDialog.show(getSupportFragmentManager(), "example dialog");
    }

    private void elimDialog() {
        ElimDialog elimDialog = new ElimDialog();
        elimDialog.show(getSupportFragmentManager(), "test dialog");
    }

    public void answer_selected(View view) {
        Button[] options = {a, b, c, d};
        for (Button button : options) {
            if (button != first_elim && button != second_elim) {
                button.setBackgroundColor(getResources().getColor(R.color.black));
            }
        }
        view.setBackgroundColor(getResources().getColor(R.color.yellow));
        chosen = view.getTag().toString();
        final_answer.setEnabled(true);
        final_answer.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public void pollUsed() {
        poll.setBackground(getResources().getDrawable(R.drawable.circle_gray, null));
        poll.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        poll.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lose_option_foreground, 0, 0);
        poll_used = true;
        editor.putBoolean("pollUsed", true);
        editor.apply();
    }

    @Override
    public void elimUsed() {
        a.setBackgroundColor(getResources().getColor(R.color.black));
        b.setBackgroundColor(getResources().getColor(R.color.black));
        c.setBackgroundColor(getResources().getColor(R.color.black));
        d.setBackgroundColor(getResources().getColor(R.color.black));
        final_answer.setEnabled(false);
        final_answer.setBackgroundColor(getResources().getColor(R.color.gray));

        Button[] options = {a, b, c, d};
        first_elim = a;
        second_elim = a;
        while (first_elim.getTag().equals(myAnswers[level]) || second_elim.getTag().equals(myAnswers[level]) || first_elim.getTag().equals(second_elim.getTag())) {
            first_elim = options[(int) (Math.random() * 4)];
            second_elim = options[(int) (Math.random() * 4)];
        }

        first_elim.setBackgroundColor(getResources().getColor(R.color.gray));
        first_elim.setEnabled(false);
        second_elim.setBackgroundColor(getResources().getColor(R.color.gray));
        second_elim.setEnabled(false);


        eliminate.setBackground(getResources().getDrawable(R.drawable.circle_gray, null));
        eliminate.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        eliminate.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.lose_option_foreground, 0, 0);
        eliminate_used = true;
        editor.putBoolean("elimUsed", true);
        editor.apply();
    }

    @Override
    public void gameOver() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void gameWon() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

