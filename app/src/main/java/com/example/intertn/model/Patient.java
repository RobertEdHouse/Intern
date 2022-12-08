package com.example.intertn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Patient implements Serializable {

    private String FirstName;
    private String LastName;
    private SexType Sex;


    private State CurrentState;
    private float Temperature;
    private int Immunity;


    private int Brain;
    private int Heart;
    private int Intestines;
    private int Liver;
    private int Lungs;
    private int Stomach;


    private List<Disease> Diseases;
    private List<Symptom> Symptoms;
    private List<Medicine> Medicines;

    public Patient(String firstName, String lastName, SexType sex, int immunity, Disease disease) {
        FirstName = firstName;
        LastName = lastName;
        Sex = sex;
        Immunity = immunity;
        Diseases=new ArrayList<>();
        Medicines=new ArrayList<>();
        Symptoms=new ArrayList<>();
        Diseases.add(disease);


        CurrentState = State.ALIVE;
        Temperature = 36.6f;
        Brain = 10;
        Heart = 10;
        Intestines = 10;
        Liver = 10;
        Lungs = 10;
        Stomach = 10;

    }


    private void raiseTemperature()
    {
        Random rand = new Random();
        Temperature += (1 - (float)Immunity / 100) * ((rand.nextInt(3 - 1) + 1) );
        Temperature = ((float)Math.ceil(Temperature * 10))/10;
    }
    private void lowerTemperature()
    {
        Random rand = new Random();
        float f = (Immunity / 100) * ((rand.nextInt(3 - 1) + 1));
        if (Temperature - f < 36.6)
            Temperature = 36.6f;
        else {
            Temperature -= f;
            Temperature = ((float)Math.ceil(Temperature * 10))/10;
        }
    }

    public void takeMedicine(Medicine medicine)
    {
        Medicines.add(medicine);
    }


    public void nextDay(List<Symptom> symptoms, Map<String, Disease> treat)
    {
        //применить лекарства
        //изменить состояния болезней
        List<Disease> activeDiseases = Diseases;
        if(Medicines.size()>0){
            for (Medicine med : Medicines)
            {
                for (Disease dis : Diseases)
                {
                    Disease d = dis;
                    if (treat.get(med.getType()).equals(d) == true)
                    {
                        if (d.isTemperature() == true)
                            lowerTemperature();
                        med.treatOrgans(this);
                        activeDiseases.remove(dis);
                        if (activeDiseases.size() == 0)
                            break;
                    }
                }
            }
        }

        List<Disease> diseases = Diseases;
        for (Disease dis : Diseases)
        {
            if (dis.getStagePercent() <= 0)
            {
                diseases.remove(dis);
            }
        }
        Diseases = diseases;

        Disease randomDisease = null;
        if (activeDiseases.size()!=0)
            randomDisease = Diseases.get((new Random()).nextInt(Diseases.size()));

        for (Disease dis : activeDiseases)
        {
            if (randomDisease.getId() == dis.getId())
            {
                randomDisease.destroyOrgans(this);
                if (randomDisease.isTemperature())
                    raiseTemperature();
                changeState();
            }
            dis.raiseStage(Immunity);
            if(Immunity>50)
                Immunity-=Immunity/((new Random()).nextInt(10-1)+1);
        }
        updateSymptoms(symptoms);

        //изменить состояния органов
        //изменить температуру в зависимости от состояния болезни
    }

    private void treat(Dictionary<String, Disease> treat)
    {

    }

    private void changeState()
    {
        if (Brain == 0 || Heart == 0 ||
                Liver == 0 || Intestines == 0 ||
                Lungs == 0 || Stomach == 0 || Temperature>=42)
            this.CurrentState = State.DEAD;
        for(Disease dis : Diseases)
        {
            if(dis.getStage()>=100)
                this.CurrentState = State.DEAD;
        }
    }
    private void updateSymptoms(List<Symptom> symptoms)
    {

        Symptoms.clear();

        for (Disease disease : Diseases)
            for (Symptom sym : symptoms)
                for (SymptomManifest symMan : disease.getListSymptom())
                {
                    if (sym.getId() == symMan.getCode())
                        Symptoms.add(sym);
                }


        //на основе болезни обновить симптомы
    }

    public Answer answer(Question question)
    {
        if(question==null){
            return new Answer(-1,getStandardAnswer());
        }
        List<Symptom> commonSymptoms = new ArrayList<>();
        for (int code : question.getSymptomCodes())
        {
            for (Symptom symptom : Symptoms)
            {
                if (code == symptom.getId())
                    commonSymptoms.add(symptom);
            }
        }
        Random rand = new Random();
        Symptom symptomRandom = commonSymptoms.get(rand.nextInt(commonSymptoms.size()));
        return symptomRandom.getAnswer();
    }
    private String getStandardAnswer(){
        return "Доброго дня\n";

    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public SexType getSex() {
        return Sex;
    }

    public State getCurrentState() {
        return CurrentState;
    }

    public float getTemperature() {
        return Temperature;
    }

    public int getBrain() {
        return Brain;
    }

    public int getHeart() {
        return Heart;
    }

    public int getIntestines() {
        return Intestines;
    }

    public int getLiver() {
        return Liver;
    }

    public int getLungs() {
        return Lungs;
    }

    public int getStomach() {
        return Stomach;
    }

    public void setBrain(int brain) {
        Brain = brain;
    }

    public void setHeart(int heart) {
        Heart = heart;
    }

    public void setIntestines(int intestines) {
        Intestines = intestines;
    }

    public void setLiver(int liver) {
        Liver = liver;
    }

    public void setLungs(int lungs) {
        Lungs = lungs;
    }

    public void setStomach(int stomach) {
        Stomach = stomach;
    }

    public int getImmunity() {
        return Immunity;
    }
}
