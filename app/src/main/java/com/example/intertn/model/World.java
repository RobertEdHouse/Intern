package com.example.intertn.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class World implements Serializable {
    private int CurrentDay;
    private int TotalDays;
    private Player Avatar;
    private LinkedList<Patient> Patients;
    private List<Patient> DeadPatients;
    private List<Symptom> Symptoms;
    private List<Disease> Diseases;
    private List<Question> Questions;
    private List<Medicine> Medicines;

    private Map<String, Disease> treat;
    private List<Patient> currentDeadPatients;
    private boolean isGame = true;

    public World()
    {
        Patients = new LinkedList<>();
        DeadPatients = new ArrayList<>();
        //заполняем Symptoms, Diseases, Questions,
        //Medicines из файла конфигурации
        treat=new HashMap<String,Disease>();
        loadFromConfig();
    }

    public void init(int CurrentDay,int TotalDays,int countPatient, int startMoney)
    {
        this.CurrentDay = CurrentDay;
        this.TotalDays = TotalDays;

//        for (int i = 0; i < countPatient; i++)
//        {
//            Patients.add(initPatient());
//        }
        Avatar = new Player(getStandardMedicines(), startMoney);
    }

    private Patient initPatient()
    {
        Random random = new Random();
        Patient newPatient = new Patient("Мартiн", "Септiм", SexType.MALE, 90, Diseases.get(random.nextInt(Diseases.size())));

        return newPatient;
    }
    private List<Medicine> getStandardMedicines()
    {
        //выбираем первые 4 лекарства в случайном
        //количественном соотношении и возвращаем
        return Medicines;
    }

    public void nextDay()
    {
        currentDeadPatients=new ArrayList<>();
        for (Patient patient : Patients)
        {
            State state=patient.getCurrentState();
            patient.nextDay(Symptoms, treat);
            if (patient.getCurrentState() == State.DEAD && state != State.DEAD)
            {
                currentDeadPatients.add(patient);
                DeadPatients.add(patient);
                Patients.remove(patient);
                if (Patients.size() == 0)
                    break;
            }
        }
        CurrentDay++;
        if (CurrentDay >= TotalDays)
        {
            endGame(Avatar.getKarma());
        }
        else if (Patients.size() == 0)
        {
            gameOver();
        }
        //начислисть зарплату в зависимости 
        //от количества погибших
        if (Avatar.getMoney() < 10 && Avatar.getMedicines().size() == 0)
            gameOver();
    }

    public void SaveGame()
    {

        //SaveLoad.SaveData(this);
    }
    public void LoadGame(WorldData worldData)
    {
        WorldData loadWorld = worldData;
        CurrentDay = loadWorld.CurrentDay;
        TotalDays = loadWorld.TotalDays;
        Avatar = loadWorld.Avatar;
        Patients = loadWorld.Patients;
        DeadPatients = loadWorld.DeadPatients;
    }


    private void loadFromConfig()
    {
        Symptoms = loadSymptoms();
        Diseases = loadDiseases();
        Questions = loadQuestions();
        Medicines = loadMedicines();
    }
    private List<Symptom> loadSymptoms()
    {
        List<Symptom> symptoms = new ArrayList<>();
        return symptoms;
    }


    private List<Disease> loadDiseases()
    {
        List<Disease> list = new ArrayList<>();
        return list;
    }
    private List<Question> loadQuestions()
    {
        List<Question> questions = new ArrayList<>();
        return questions;
    }

    private List<Medicine> loadMedicines()
    {
        //заполнить из файла конфигурации
        List<Medicine> list = new ArrayList<>();

        return list;
    }

    public void endGame(int Karma)
    {
        //Console.WriteLine("End Game");
        isGame = false;
        return;
    }
    public void gameOver()
    {
        //Console.WriteLine("Game Over");
        isGame = false;
        return;
    }

    public Medicine getMedicine(String type){
        for (Medicine m:Medicines) {
            if(m.getType().equals(type))
                return m;
        }
        return null;
    }

    public List<Patient> getCurrentDeadPatients() {
        return currentDeadPatients;
    }

    public int getCurrentDay() {
        return CurrentDay;
    }

    public int getTotalDays() {
        return TotalDays;
    }

    public Player getAvatar() {
        return Avatar;
    }

    public LinkedList<Patient> getPatients() {
        return Patients;
    }

    public List<Patient> getDeadPatients() {
        return DeadPatients;
    }

    public List<Symptom> getSymptoms() {
        return Symptoms;
    }

    public List<Disease> getDiseases() {
        return Diseases;
    }

    public List<Question> getQuestions() {
        return Questions;
    }

    public List<Medicine> getMedicines() {
        return Medicines;
    }

    public Map<String, Disease> getTreat() {
        return treat;
    }

    public boolean isGame() {
        return isGame;
    }




    ////////////test load methods

    public void setTestPatient(LinkedList<Patient> patients){
        this.Patients=patients;
    }
    public void setTestMedicines(List<Medicine> medicines){
        this.Medicines=medicines;
    }
    public void setTestQuestion(List<Question> questions){
        this.Questions=questions;
    }
    public void setTestSymptom(List<Symptom> symptoms){
        this.Symptoms=symptoms;
    }
    public void setTestDisease(List<Disease> diseases){
        this.Diseases=diseases;
    }
    public void setTestTreat(String m, Disease d){
        treat.put(m,Diseases.get(0));
    }
}
