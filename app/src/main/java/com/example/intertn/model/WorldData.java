package com.example.intertn.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class WorldData implements Serializable {
    public int CurrentDay;
    public int TotalDays;
    public Player Avatar;
    public LinkedList<Patient> Patients;
    public List<Patient> DeadPatients;

    public WorldData(int CurrentDay,int TotalDays, Player Avatar, LinkedList<Patient> Patients, List<Patient> DeadPatients)
    {
        this.CurrentDay=CurrentDay;
        this.TotalDays=TotalDays;
        this.Avatar=Avatar;
        this.Patients=Patients;
        this.DeadPatients=DeadPatients;
    }
}
