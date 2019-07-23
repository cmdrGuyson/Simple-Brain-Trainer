package com.example.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView space0;
    TextView space1;
    TextView space2;
    TextView space3;
    TextView endText;
    TextView scoreView;
    TextView question;
    TextView timeView;
    TextView goButton;
    Button restartButton;
    ArrayList<Integer> answerList;
    int numberTotal = 0;
    int numberCorrect = 0;
    int assignedSpace;
    int time;
    boolean gameActive = false;
    boolean gameOver = false;
    CountDownTimer countDownTimer;

    public void startGame(View view){
        goButton.setVisibility(View.INVISIBLE);
        space0.setVisibility(View.VISIBLE);
        space1.setVisibility(View.VISIBLE);
        space2.setVisibility(View.VISIBLE);
        space3.setVisibility(View.VISIBLE);
        scoreView.setVisibility(View.VISIBLE);
        timeView.setVisibility(View.VISIBLE);
        endText.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        gameActive = true;
        timerMethod();
    }

    public void onClickSpace(View view){

        if(gameActive){

            TextView spaceView = (TextView) view;
            int tappedSpace = Integer.parseInt(spaceView.getTag().toString());
            Log.i("Log: ", "TAPPED SPACE" + tappedSpace);

            if (tappedSpace == assignedSpace) {
                numberTotal += 1;
                numberCorrect += 1;
                Log.i("Log: ", "CORRECT");
                endText.setText("Correct!");
            } else {
                numberTotal += 1;
                Log.i("Log: ", "INCORRECT");
                endText.setText("Wrong!");
            }

            scoreView.setText(numberCorrect + "/" + numberTotal);
            answerList.clear();
            generatingQuestion();
        }
    }

    public void generatingQuestion(){
        Random random = new Random();
        int firstNumber = random.nextInt(50) + 1;   //First Number to add in the Question
        int secondNumber = random.nextInt(50) + 1;  //Second Number to add in the Question
        int correctAnswer = firstNumber + secondNumber;
        assignedSpace = random.nextInt(4);
        Log.i("Log: ", "ASSIGNED SPACE" + assignedSpace);
        int incorrectAnswer;

        for(int i=0; i<4; i++){
            if(i==assignedSpace){
                answerList.add(correctAnswer);
            }else{
                incorrectAnswer = random.nextInt(50) + 1;
                while(incorrectAnswer==correctAnswer){
                    incorrectAnswer = random.nextInt(50) + 1;
                }
                answerList.add(incorrectAnswer);
            }
        }

        Log.i("Log: ", answerList.toString());

        space0.setText(Integer.toString(answerList.get(0)));
        space1.setText(Integer.toString(answerList.get(1)));
        space2.setText(Integer.toString(answerList.get(2)));
        space3.setText(Integer.toString(answerList.get(3)));

        String questionGiven = firstNumber + "+" + secondNumber;
        question.setText(questionGiven);

    }

    public void timerMethod(){

        if(gameActive) {

            countDownTimer = new CountDownTimer(21000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    time = time - 1;
                    timeView.setText(time + "s");
                }

                @Override
                public void onFinish() {
                    Log.i("LOG", "OVER!");
                    gameActive = false;
                    endText.setText("Game Over! Your Score: " + numberCorrect + "/" + numberTotal);
                    restartButton.setVisibility(View.VISIBLE);
                    gameOver = true;
                }
            }.start();
        }
    }

    public void restartFunction(View view){
        if(gameOver){
            gameActive=true;
            gameOver=false;
            answerList.clear();
            endText.setText("");
            restartButton.setVisibility(View.INVISIBLE);
            time = 21;
            numberTotal = 0;
            numberCorrect = 0;
            generatingQuestion();
            scoreView.setText("0/0");
            timerMethod();
        }
    }







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        space0 = (TextView) findViewById(R.id.space0);
        space1 = (TextView) findViewById(R.id.space1);
        space2 = (TextView) findViewById(R.id.space2);
        space3 = (TextView) findViewById(R.id.space3);
        endText = (TextView) findViewById(R.id.endText);
        scoreView = (TextView) findViewById(R.id.scoreView);
        question = (TextView) findViewById(R.id.question);
        timeView = (TextView) findViewById(R.id.timeView);
        goButton = (TextView) findViewById(R.id.goButton);
        restartButton = (Button) findViewById(R.id.restartButton);
        answerList = new ArrayList<Integer>();
        time = 21;

        goButton.setVisibility(View.VISIBLE);
        space0.setVisibility(View.INVISIBLE);
        space1.setVisibility(View.INVISIBLE);
        space2.setVisibility(View.INVISIBLE);
        space3.setVisibility(View.INVISIBLE);
        scoreView.setVisibility(View.INVISIBLE);
        timeView.setVisibility(View.INVISIBLE);
        endText.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        scoreView.setText("0/0");
        generatingQuestion();
    }
}
