package com.khasang.myfirstquiz3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {
    private TextView tvQuestion;
    private Button btnTrue;
    private Button btnFalse;
    private Button btnNext;
    private int currentIndex;
    private QuizHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        helper = new QuizHelper(this);

        InitializeWidgets();
        InitializeCards();

        if (isStartPermission()) {
            startQuiz();
        } else {
            showToast(getString(R.string.txt_something_wrong));
        }
    }

    private void InitializeWidgets() {
        tvQuestion = (TextView) findViewById(R.id.tvQuestion);
        btnTrue = (Button) findViewById(R.id.btnTrue);
        btnFalse = (Button) findViewById(R.id.btnFalse);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultToastForButton(true);
            }
        });

        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showResultToastForButton(false);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextQuestion();
            }
        });
    }

    private void showResultToastForButton(boolean b) {
        showToast(Boolean.toString(b == helper.getCurrentAnswer()));
    }

    private void InitializeCards() { helper.initCards(); }

    private boolean isStartPermission() { return helper.isReady(); }

    private void startQuiz() { showNextQuestion(); }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showNextQuestion() { tvQuestion.setText(helper.getNextQuestion()); }
}
