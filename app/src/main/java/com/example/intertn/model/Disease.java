package com.example.intertn.model;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Disease implements Serializable {
    private int Id;
    private String Name;
    private int MaxStage;
    private int StagePercent;
    private int Brain=0;
    private int Heart=0;
    private int Intestines=0;
    private int Liver=0;
    private int Lungs=0;
    private int Stomach=0;
    private boolean Temperature;
    private List<SymptomManifest> SymptomsManifest;

    public Disease(int id, String name, int maxStage, List<SymptomManifest> symptomsManifest) {
        Id = id;
        Name = name;
        MaxStage = maxStage;
        this.StagePercent = 10;
        SymptomsManifest = symptomsManifest;
    }

    public int getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getMaxStage() {
        return MaxStage;
    }

    public int getStagePercent() {
        return StagePercent;
    }

    public boolean isTemperature() {
        return Temperature;
    }

    public void setParamOrgan(List<Integer>list,boolean change)
    {
        if (list.size() > 0)
            Brain = list.get(0);
        if (list.size() > 1)
            Heart = list.get(1);
        if (list.size() > 2)
            Intestines = list.get(2);
        if (list.size() > 3)
            Liver = list.get(3);
        if (list.size() > 4)
            Lungs = list.get(4);
        if (list.size() > 5)
            Stomach = list.get(5);
        Temperature = change;
    }
    public void raiseStage(int Immunity)
    {
        Random rand = new Random();
        StagePercent += Math.ceil(((100-Immunity)/100)*rand.nextInt(30-5)+5);
        if (StagePercent > 100)
            StagePercent = 100;
    }

    public void downStage(int Immunity)
    {
        Random rand = new Random();
        StagePercent -= Math.ceil(((Immunity)/100)*rand.nextInt(30-5)+5);
        if (StagePercent < 0)
            StagePercent = 0;
    }

    public int getStage()
    {
        return StagePercent/(100/MaxStage);
    }

    public List<SymptomManifest> getListSymptom()
    {
        return SymptomsManifest;
    }

    public void destroyOrgans(Patient patient)
    {
//        int k = 100 / 6;
//        int currentStage = StagePercent / (100 / MaxStage)+1;
//        for (int i = 0; i < 4; i++)
//        {
//
//        }
        int stageOrgan=0;
        int k=0;
        if(patient.getImmunity()>50)
            k=1;
        stageOrgan= (int) Math.ceil(patient.getBrain()+k-Brain);
        if (stageOrgan >= 0)
            patient.setBrain(stageOrgan);
        else if (stageOrgan > 10)
            patient.setBrain(10);
        else
            patient.setBrain(0);

        stageOrgan=(int) Math.ceil((patient.getHeart()+k-Heart));
        if (stageOrgan > 10)
            patient.setHeart(10);
        else if (stageOrgan >= 0)
            patient.setHeart(stageOrgan);
        else
            patient.setHeart(0);

        stageOrgan=(int) Math.ceil((patient.getLiver()+k-Liver));
        if (stageOrgan > 10)
            patient.setLiver(10);
        else if (stageOrgan >= 0)
            patient.setLiver(stageOrgan);
        else
            patient.setLiver(0);

        stageOrgan=(int) Math.ceil(patient.getLungs()+k-Lungs);
        if (stageOrgan > 10)
            patient.setLungs(10);
        else if (stageOrgan >= 0)
            patient.setLungs(stageOrgan);
        else
            patient.setLungs(0);

        stageOrgan=(int) Math.ceil((patient.getStomach()+k-Stomach));
        if (stageOrgan > 10)
            patient.setStomach(10);
        else if (stageOrgan >= 0)
            patient.setStomach(stageOrgan);
        else
            patient.setStomach(0);

        stageOrgan=(int) Math.ceil((patient.getIntestines()+k-Intestines));
        if (stageOrgan > 10)
            patient.setIntestines(10);
        else if (stageOrgan >= 0)
            patient.setIntestines(stageOrgan);
        else
            patient.setIntestines(0);
    }
}
