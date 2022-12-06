package com.example.intertn.model;

import java.util.List;
import java.util.Random;

public class Disease {
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
    public void raiseStage()
    {
        Random rand = new Random();
        StagePercent += rand.nextInt(30-5)+5;
        if (StagePercent > 100)
            StagePercent = 100;
    }

    public void downStage()
    {
        Random rand = new Random();
        StagePercent -= rand.nextInt(30-5)+5;
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
        int k = 100 / 6;
        int currentStage = StagePercent / (100 / MaxStage)+1;
        for (int i = 0; i < 4; i++)
        {

        }
        int stageOrgan=0;

        stageOrgan=patient.getBrain()-Brain;
        if (stageOrgan >= 0)
            patient.setBrain(stageOrgan);
        else
            patient.setBrain(0);

        stageOrgan=patient.getHeart()-Heart;
        if (stageOrgan >= 0)
            patient.setHeart(stageOrgan);
        else
            patient.setHeart(0);

        stageOrgan=patient.getLiver()-Liver;
        if (stageOrgan >= 0)
            patient.setLiver(stageOrgan);
        else
            patient.setLiver(0);

        stageOrgan=patient.getLungs()-Lungs;
        if (stageOrgan >= 0)
            patient.setLungs(stageOrgan);
        else
            patient.setLungs(0);

        stageOrgan=patient.getStomach()-Stomach;
        if (stageOrgan >= 0)
            patient.setStomach(stageOrgan);
        else
            patient.setStomach(0);

        stageOrgan=patient.getIntestines()-Intestines;
        if (stageOrgan >= 0)
            patient.setIntestines(stageOrgan);
        else
            patient.setIntestines(0);
    }
}
