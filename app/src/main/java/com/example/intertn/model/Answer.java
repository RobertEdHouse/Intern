package com.example.intertn.model;

import java.io.Serializable;

public class Answer implements Serializable {

    private int Id;
    private String Text;

    public Answer(int Id, String Text)
    {
        this.Id = Id;
        this.Text = Text;
    }

    public int getId() {
        return Id;
    }

    public String getText() {
        return Text;
    }
}
