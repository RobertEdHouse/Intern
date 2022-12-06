package com.example.intertn.model;

public class Dialog {
    private int Id;
    private String Question;
    private String Answer;
    private Patient PatientI;
    private int Day;

    public Dialog(Patient patientI, String question, String answer, int day) {
        Question = question;
        Answer = answer;
        PatientI = patientI;
        Day = day;
    }

    public int getId() {
        return Id;
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public Patient getPatient()
    {
        return PatientI;
    }
    public int getDay() {
        return Day;
    }
}
