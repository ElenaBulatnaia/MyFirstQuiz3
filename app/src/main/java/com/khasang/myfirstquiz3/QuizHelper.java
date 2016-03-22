package com.khasang.myfirstquiz3;

import android.content.Context;
import android.graphics.Typeface;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by macbookair on 17.03.16.
 */
public class QuizHelper {
    private final static String TYPEFACE = "ACUTATR_.TTF";

    private Context context;
    private ArrayList<Card> cards;
    private int currentIndex;

    private String[] questions;
    private boolean[] answers;

    public QuizHelper(WeakReference<Context> context) { this.context = context.get(); }

    public void initCards() {
        String[] cardArray = context.getResources().getStringArray(R.array.cards);

        cards = new ArrayList<>();

        for (String card : cardArray) {
            String[] qa = card.split("/");
            if (qa.length > 1) {
                Card newCard = new Card(qa[0], qa[1].trim().equals("1"));
                cards.add(newCard);
            }
        }
    }

    public boolean isReady() {
        return cards != null && !cards.isEmpty();
    }

    public String getNextQuestion() {
        int oldIndex = currentIndex;
        do {
            currentIndex = (int) (Math.random() * cards.size());
        } while (oldIndex == currentIndex);

        return cards.get(currentIndex).getQuestion();
    }

    public boolean getCurrentAnswer() {
        return cards.get(currentIndex).getAnswer();
    }

    public String getStringResultForAnswer(boolean gotAnswer) {
        return gotAnswer == getCurrentAnswer() ? 
                context.getString(R.string.txt_right_answer) : 
                context.getString(R.string.txt_wrong_answer);
    }

    public Typeface getCurrentFont() {
        return Typeface.createFromAsset(context.getAssets(), TYPEFACE);
    }
}

