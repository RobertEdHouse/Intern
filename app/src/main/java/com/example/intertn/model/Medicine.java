package com.example.intertn.model;

import java.util.List;

public class Medicine {
    private int Id;
    private String Type;
    private int Count;
    private int Price;
    private int Brain = 0;
    private int Heart = 0;
    private int Intestines = 0;
    private int Liver = 0;
    private int Lungs = 0;
    private int Stomach = 0;

    public Medicine(int id, String type, int count, int price) {
        Id = id;
        Type = type;
        Count = count;
        Price = price;
    }

    public void setParamOrgan(List<Integer> list)
    {
        if(list.size()>0)
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
    }
    public void used()
    {
        Count--;
    }
    public void add(int count)
    {
        Count+=count;
    }

    public void treatOrgans(Patient patient)
    {
        int stageOrgan=0;

        stageOrgan=patient.getBrain()+Brain;
        if (stageOrgan >= 10)
            patient.setBrain(stageOrgan);
        else
            patient.setBrain(10);

        stageOrgan=patient.getHeart()+Heart;
        if (stageOrgan >= 10)
            patient.setHeart(stageOrgan);
        else
            patient.setHeart(10);

        stageOrgan=patient.getLiver()+Liver;
        if (stageOrgan >= 10)
            patient.setLiver(stageOrgan);
        else
            patient.setLiver(10);

        stageOrgan=patient.getLungs()+Lungs;
        if (stageOrgan >= 10)
            patient.setLungs(stageOrgan);
        else
            patient.setLungs(10);

        stageOrgan=patient.getStomach()+Stomach;
        if (stageOrgan >= 10)
            patient.setStomach(stageOrgan);
        else
            patient.setStomach(10);

        stageOrgan=patient.getIntestines()+Intestines;
        if (stageOrgan >= 10)
            patient.setIntestines(stageOrgan);
        else
            patient.setIntestines(10);
    }
    public int getId() {
        return Id;
    }

    public String getType() {
        return Type;
    }

    public int getCount() {
        return Count;
    }

    public int getPrice() {
        return Price;
    }
}
