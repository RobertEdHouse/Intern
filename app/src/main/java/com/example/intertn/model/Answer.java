package com.example.intertn.model;

public class Answer {

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
