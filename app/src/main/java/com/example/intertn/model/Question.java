package com.example.intertn.model;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {
    private int Id;
    private String Text;
    private List<Integer> SymptomCodes;

    public Question(int id, String text, List<Integer> symptomCodes) {
        Id = id;
        Text = text;
        SymptomCodes = symptomCodes;
    }

    public int getId() {
        return Id;
    }

    public String getText() {
        return Text;
    }

    public List<Integer> getSymptomCodes() {
        return SymptomCodes;
    }
}
