package com.khasang.myfirstquiz3;

import android.content.Context;

/**
 * Created by macbookair on 17.03.16.
 */
public class QuizHelper {
    private Context context;
    private String[] questions;
    private boolean[] answers;
    private int currentIndex;

    public QuizHelper(Context context) {
        this.context = context;
    }

    public void initCards() {
        questions = context.getResources().getStringArray(R.array.questions);

        int[] as = context.getResources().getIntArray(R.array.answers);
        answers = new boolean[as.length];
        for (int i = 0; i < as.length; i++) {
            answers[i] = as[i] == 1;
        }
    }

    public boolean isReady() {
        return questions.length == answers.length;
    }

    public String getNextQuestion() {
        int oldIndex = currentIndex;
        do {
            currentIndex = (int) (Math.random() * questions.length);
        } while (oldIndex == currentIndex);
        return questions[currentIndex];
    }

    public boolean getCurrentAnswer() {
        return answers[currentIndex];
    }
}

