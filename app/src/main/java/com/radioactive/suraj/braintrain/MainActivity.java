package com.radioactive.suraj.braintrain;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends Activity {

    Button startButton;
    TextView resultTextView;
    TextView pointsTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    RelativeLayout gameRelativeLayout;

    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestions = 0;



    public void playAgain(View view) {

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        score = 0;
        numberOfQuestions = 0;

        timerTextView.setText("30s");
        pointsTextView.setText("0/0");
        resultTextView.setText("");
        playAgainButton.setVisibility(View.INVISIBLE);

        generateQuestion();

        new CountDownTimer(30100, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                playAgainButton.setVisibility(View.VISIBLE);
                timerTextView.setText("0s");
                resultTextView.setText("Your score: " + Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();


    }

    public void generateQuestion() {

        Random rand = new Random();


        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        int incorrectAnswer;

        int randomMath = rand.nextInt(4);

        switch(randomMath) {
            case 0 :
                sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));
                locationOfCorrectAnswer = rand.nextInt(4);
                answers.clear();
                for (int i=0; i<4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a + b);
                    } else {
                        incorrectAnswer = rand.nextInt(41);
                        while (incorrectAnswer == a + b) {
                            incorrectAnswer = rand.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;

            case 1 :
                sumTextView.setText(Integer.toString(a) + " - " + Integer.toString(b));
                locationOfCorrectAnswer = rand.nextInt(4);
                answers.clear();
                for (int i=0; i<4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a - b);
                    } else {
                        incorrectAnswer = rand.nextInt(41);
                        while (incorrectAnswer == a - b) {
                            incorrectAnswer = rand.nextInt(41);
                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;

            case 2 :
                sumTextView.setText(Integer.toString(a) + " X " + Integer.toString(b));
                locationOfCorrectAnswer = rand.nextInt(4);
                answers.clear();
                for (int i=0; i<4; i++) {
                    if (i == locationOfCorrectAnswer) {
                        answers.add(a * b);
                    } else {
                        incorrectAnswer = rand.nextInt(401);
                        while (incorrectAnswer == a * b) {
                            incorrectAnswer = rand.nextInt(401);
                        }
                        answers.add(incorrectAnswer);
                    }
                }
                break;

            case 3 :
                if((a>b)&&(a%b==0)) {
                    sumTextView.setText(Integer.toString(a) + " / " + Integer.toString(b));
                    locationOfCorrectAnswer = rand.nextInt(4);
                    answers.clear();
                    for (int i = 0; i < 4; i++) {
                        if (i == locationOfCorrectAnswer) {
                            answers.add(a / b);
                        } else {
                            incorrectAnswer = rand.nextInt(41);
                            while (incorrectAnswer == a - b) {
                                incorrectAnswer = rand.nextInt(41);
                            }
                            answers.add(incorrectAnswer);
                        }
                    }
                }
                answers.clear();
                generateQuestion();
                break;
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void chooseAnswer(View view) {

        if (view.getTag().toString().equals(Integer.toString(locationOfCorrectAnswer))) {
            score++;
            resultTextView.setText("Right!");
        } else {
            resultTextView.setText("Wrong!");
        }
        numberOfQuestions++;
        pointsTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        generateQuestion();

    }
    public void start(View view) {
        startButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(findViewById(R.id.playAgainButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button)findViewById(R.id.startButton);
        sumTextView = (TextView)findViewById(R.id.sumTextView);
        button0 = (Button)findViewById(R.id.button0);
        button1 = (Button)findViewById(R.id.button1);
        button2 = (Button)findViewById(R.id.button2);
        button3 = (Button)findViewById(R.id.button3);
        resultTextView = (TextView)findViewById(R.id.resultTextView);
        pointsTextView = (TextView)findViewById(R.id.pointsTextView);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);
        gameRelativeLayout = (RelativeLayout)findViewById(R.id.gameRelativeLayout);

    }
}
