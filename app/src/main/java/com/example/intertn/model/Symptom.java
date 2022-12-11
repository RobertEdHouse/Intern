package com.example.intertn.model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Symptom implements Serializable {
    private int Id;
    private String Name;
    private List<Integer> AnswersManifest;

    public Symptom(int id, String name, List<Integer> answersManifest) {
        Id = id;
        Name = name;
        AnswersManifest = answersManifest;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }


    public Answer getAnswer()
    {
        Random rand = new Random();
        int answerCode = AnswersManifest.get(rand.nextInt(AnswersManifest.size()));
        Answer answer = loadAnswer(answerCode);
        return answer;
    }

    public Answer getAnswer(int questionCode)
    {
        Random rand = new Random();
        int answerCode = AnswersManifest.get(questionCode);
        Answer answer = loadAnswer(answerCode);
        return answer;
    }

    private Answer loadAnswer(int code)
    {
        List<Answer> answers = LoadConfig.readAnswers();
        for(Answer a : answers){
        if (a.getId() == code)
            return a;
    }
        return null;
    }
}
