package com.example.intertn.model;

import java.io.Serializable;
import java.util.ArrayList;
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
        //случайно выбираем ответ из списка возможных
        int answerCode = AnswersManifest.get(rand.nextInt(AnswersManifest.size()));
        Answer answer = loadAnswer(answerCode);
        //из конфигурационного файла считываем информацию
        //про ответ с Id равным answerCode и создаем объект Answer
        //возвращаем этот объект
        return answer;
    }

    private Answer loadAnswer(int code)
    {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer(0, "Голова така важка"));
        answers.add(new Answer(1, "Такi болi в головi"));
        answers.add(new Answer(2, "Нудить цiлий день"));
        answers.add(new Answer(3, "Живот болить"));
        answers.add(new Answer(4, "Немає сил"));
        answers.add(new Answer(5, "Не можу встати з лiжка"));

        for(Answer a : answers){
        if (a.getId() == code)
            return a;
    }
        return null;
    }
}
