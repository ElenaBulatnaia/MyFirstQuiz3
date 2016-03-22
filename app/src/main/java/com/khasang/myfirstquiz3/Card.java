package com.khasang.myfirstquiz3;

/**
 * Created by macbookair on 20.03.16.
 */
public class Card {
    private String question;
    private boolean answer;

    public Card(String question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() { return question; }

    public boolean getAnswer() { return answer; }
}
