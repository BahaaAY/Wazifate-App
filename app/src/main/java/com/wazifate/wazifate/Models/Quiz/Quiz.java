package com.wazifate.wazifate.Models.Quiz;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Quiz {
    private int id;
    private String name;

    @SerializedName("image")
    private String img;


    private List<QuizQuestion> questions = null;

    public Quiz(int id, String name, String img, List<QuizQuestion> questions) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.questions = questions;
    }

    public Quiz(int id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
