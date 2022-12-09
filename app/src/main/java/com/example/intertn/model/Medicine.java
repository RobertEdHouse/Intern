package com.example.intertn.model;

import java.io.Serializable;
import java.util.List;

public class Medicine implements Serializable {
    private int Id;
    private String Type;
    private int Count;
    private int Price;
    public int Brain = 0;
    public int Heart = 0;
    public int Intestines = 0;
    public int Liver = 0;
    public int Lungs = 0;
    public int Stomach = 0;
    private int Power=0;

    public Medicine(int id, String type, int count, int price,int power) {
        Id = id;
        Type = type;
        Count = count;
        Price = price;
        Power=power;
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
        if(Count!=0)
            Count--;
    }
    public void add()
    {
        Count++;
    }
    public Medicine getPill(){
        Medicine pill=new Medicine(this.Id,this.Type,1,this.Price,this.Power);
        pill.Brain=this.Brain;
        pill.Heart=this.Heart;
        pill.Intestines=this.Intestines;
        pill.Lungs=this.Lungs;
        pill.Liver=this.Liver;
        pill.Stomach=this.Stomach;
        return pill;
    }


    public void treatOrgans(Patient patient)
    {
        int stageOrgan=0;

        stageOrgan=patient.getBrain()+Brain;
        if (stageOrgan < 10)
            patient.setBrain(stageOrgan);
        else
            patient.setBrain(10);

        stageOrgan=patient.getHeart()+Heart;
        if (stageOrgan < 10)
            patient.setHeart(stageOrgan);
        else
            patient.setHeart(10);

        stageOrgan=patient.getLiver()+Liver;
        if (stageOrgan < 10)
            patient.setLiver(stageOrgan);
        else
            patient.setLiver(10);

        stageOrgan=patient.getLungs()+Lungs;
        if (stageOrgan < 10)
            patient.setLungs(stageOrgan);
        else
            patient.setLungs(10);

        stageOrgan=patient.getStomach()+Stomach;
        if (stageOrgan < 10)
            patient.setStomach(stageOrgan);
        else
            patient.setStomach(10);

        stageOrgan=patient.getIntestines()+Intestines;
        if (stageOrgan < 10)
            patient.setIntestines(stageOrgan);
        else
            patient.setIntestines(10);
    }

    public int getId() {
        return Id;
    }

    public int getPower() {
        return Power;
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
