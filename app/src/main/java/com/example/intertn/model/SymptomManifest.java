package com.example.intertn.model;

import java.io.Serializable;

public class SymptomManifest implements Serializable {
    private int Code;
    private int Probability;

    public SymptomManifest(int Code, int Probability)
    {
        this.Code = Code;
        this.Probability = Probability;
    }

    public int getCode(){
        return Code;
    }
    public int getProbability(){
        return Probability;
    }
}
