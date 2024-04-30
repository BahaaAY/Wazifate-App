package com.wazifate.wazifate.Models.Quiz;

import com.google.gson.annotations.SerializedName;

public class QuizQuestionChoice {
    private int id;
    @SerializedName("question_id")
    private int questionId;
    private String choice;
    @SerializedName("correct")
    private boolean isCorrect;

    public QuizQuestionChoice(int id, int questionId, String choice, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.choice = choice;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "QuizQuestionChoice{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", choice='" + choice + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}

