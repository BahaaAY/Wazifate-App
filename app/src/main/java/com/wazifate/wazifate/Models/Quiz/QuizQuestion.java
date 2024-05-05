package com.wazifate.wazifate.Models.Quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuizQuestion {
    private int id ;

    @SerializedName("quiz_id")
    private int quizId;
    private String question;

    @SerializedName("choices")
    private List<QuizQuestionChoice> choices;

    public QuizQuestion(int id, int quizId, String question, List<QuizQuestionChoice> choices) {
        this.id = id;
        this.quizId = quizId;
        this.question = question;
        this.choices = choices;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuizId() {
        return quizId;
    }

    public void setQuizId(int quizId) {
        this.quizId = quizId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuizQuestionChoice> getChoices() {
        return choices;
    }

    public void setChoices(List<QuizQuestionChoice> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        return "QuizQuestion{" +
                "id=" + id +
                ", quizId=" + quizId +
                ", question='" + question + '\'' +
                ", choices=" + choices +
                '}';
    }

    public String getCorrectAnswer() {
        System.out.println("choices: " + choices.toString());
        for (QuizQuestionChoice choice : choices) {
            if (choice.isCorrect()) {
                return choice.getChoice();
            }
        }
        return null;
    }
}
