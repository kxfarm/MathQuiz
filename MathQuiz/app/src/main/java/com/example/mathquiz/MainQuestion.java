package com.example.mathquiz;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainQuestion extends AppCompatActivity {

    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView tv_score, tv_timer, tv_questions, tv_message;
    ProgressBar progress_timer;

    int secondsRemaining = 30;
    Game g = new Game();
    CountDownTimer timer = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long l) {
            secondsRemaining--;
            tv_timer.setText(Integer.toString(secondsRemaining)+" sec");
            progress_timer.setProgress(30 - secondsRemaining);
        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);
            tv_message.setText("Time is up !" +g.getNumberCorrect()+"/"+ (g.getTotalQuestions()-1));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            },4000);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_question);

        btn_start = findViewById(R.id.btnStart);
        btn_answer0 = findViewById(R.id.btn_ans1);
        btn_answer1 = findViewById(R.id.btn_ans2);
        btn_answer2 = findViewById(R.id.btn_ans3);
        btn_answer3 = findViewById(R.id.btn_ans4);

        tv_score = findViewById(R.id.tv_score);
        tv_timer = findViewById(R.id.tv_timer);
        tv_questions = findViewById(R.id.tv_questions);
        tv_message = findViewById(R.id.tv_message);
        progress_timer = findViewById(R.id.progress_timer);

        tv_timer.setText("0 sec");
        tv_questions.setText("");
        tv_message.setText("Start the Quiz");
        tv_score.setText("0 points");

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button startButton =(Button) view;
                secondsRemaining=30;
                g=new Game();
                startButton.setVisibility(View.INVISIBLE);
                nextGame();
                timer.start();
            }
        };
        View.OnClickListener answerButtonClickListener= new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button btnClicked = (Button) view;
                int answerSelected = Integer.parseInt(btnClicked.getText().toString());
                g.checkAns(answerSelected);
                tv_score.setText(Integer.toString(g.getScore()));
                nextGame();
            }

        };

        btn_start.setOnClickListener(startButtonClickListener);
        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);

    }

    private void nextGame(){
        g.makeNewQuestion();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answer[0]));
        btn_answer1.setText(Integer.toString(answer[1]));
        btn_answer2.setText(Integer.toString(answer[2]));
        btn_answer3.setText(Integer.toString(answer[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        tv_questions.setText(g.getCurrentQuestion().getQuestionLine());

        tv_message.setText(g.getNumberCorrect()+"/"+ (g.getTotalQuestions()-1));

    }

}