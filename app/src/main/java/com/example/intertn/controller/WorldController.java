package com.example.intertn.controller;

import com.example.intertn.model.Dialog;
import com.example.intertn.model.Patient;
import com.example.intertn.model.Question;
import com.example.intertn.model.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorldController implements Serializable {
    private World world;
    private Patient currentPatient;
    private int currentDay=0;
    public WorldController(World world){
        this.world=world;
    }

    public WorldController(){
        this.world=new World();
        world.init(0,5,1,1000);
        nextDay();
    }

    public List<String> getQuestion(){
        List<String> listQuestionString=new ArrayList<>();
        for(Question q : world.getQuestions()){
            listQuestionString.add(q.getText());
        }
        return listQuestionString;
    }
    public String getStartAnswer(){
        return currentPatient.answer(null).getText();
    }

    public String getAnswer(int id_question){
        if(world.getQuestions().size()<=id_question)
            return "Не знаю...";
        return world.getAvatar().ask(world.getQuestions().get(id_question),currentPatient,currentDay).getText();
    }

    public void setCurrentPatient(Patient patient){
        currentPatient=patient;
    }

    public float getPatientTemperature(){
        return currentPatient.getTemperature();
    }

    public List<String> getDialogs(){
        List<String>dialogsStr=new ArrayList<>();
        List<Dialog>dialogs=world.getAvatar().getDialogs(currentDay,currentPatient);
        for (Dialog d : dialogs){
            String str="[Питання]\n"+d.getQuestion()+"\n";
            str+="[Пацієнт]\n"+d.getAnswer()+"\n";
            dialogsStr.add(str);
        }
        return dialogsStr;
    }
    public boolean nextPatient(){
        int currentIndex=world.getPatients().indexOf(currentPatient);
        if(currentIndex+1>=world.getPatients().size()){
            return false;
        }
        currentPatient=world.getPatients().listIterator(currentIndex).next();
        return true;
    }
    public List<String> getCurrentDeadPatient(){
        List<String> patients=new ArrayList<>();
        for(Patient patient : world.getCurrentDeadPatients()){
            String str=patient.getFirstName()+" "+patient.getLastName()+" ";
            str+="загинув!\n";
            patients.add(str);
        }
        return patients;
    }
    public void nextDay(){
        if(!world.isGame())
            return;
        setCurrentPatient(world.getPatients().get(0));
        world.nextDay();
        currentDay=world.getCurrentDay();

    }

    public int getCurrentDay(){
        return currentDay;
    }

    public boolean isGame() {
        return world.isGame();
    }
}
