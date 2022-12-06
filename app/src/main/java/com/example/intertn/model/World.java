package com.example.intertn.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class World {
    private int CurrentDay;
    private int TotalDays;
    private Player Avatar;
    private List<Patient> Patients;
    private List<Patient> DeadPatients;
    private List<Symptom> Symptoms;
    private List<Disease> Diseases;
    private List<Question> Questions;
    private List<Medicine> Medicines;

    private Map<String, Disease> treat;
    private boolean isGame = true;

    public World()
    {
        Patients = new ArrayList<>();
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

        for (int i = 0; i < countPatient; i++)
        {
            Patients.add(initPatient());
        }
        Avatar = new Player(getStandardMedicines(), startMoney);
    }

    private Patient initPatient()
    {
        Random random = new Random();
        Patient newPatien = new Patient("Мартiн", "Септiм", SexType.MALE, 90, Diseases.get(random.nextInt(Diseases.size())));

        return newPatien;
    }
    private List<Medicine> getStandardMedicines()
    {
        //выбираем первые 4 лекарства в случайном
        //количественном соотношении и возвращаем
        return Medicines;
    }

    public void nextDay()
    {

        for (Patient patient : Patients)
        {
            patient.nextDay(Symptoms, treat);
            if (patient.getCurrentState() == State.DEAD)
            {
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
    public void LoadGame()
    {
//        WorldData loadWorld = SaveLoad.LoadData();
//        CurrentDay = loadWorld.CurrentDay;
//        TotalDays = loadWorld.TotalDays;
//        Avatar = loadWorld.Avatar;
//        Patients = loadWorld.Patients;
//        DeadPatients = loadWorld.DeadPatients;
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
        List<Integer> answers = new ArrayList<>();
        answers.add(0);
        answers.add(1);
        List<Symptom> symptoms = new ArrayList<>();
        symptoms.add(new Symptom(0, "Головний бiль",answers));
        answers = new ArrayList<>();
        answers.add(2);
        answers.add(3);
        symptoms.add(new Symptom(1, "Бiль у животi", answers));
        answers = new ArrayList<>();
        answers.add(4);
        answers.add(5);
        symptoms.add(new Symptom(2, "Слабкість", answers));
        return symptoms;
    }


    private List<Disease> loadDiseases()
    {
        Random random = new Random();
        List<SymptomManifest> listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(0, random.nextInt(100)));
        listSymptoms.add(new SymptomManifest(1, random.nextInt(100)));
        listSymptoms.add(new SymptomManifest(2, random.nextInt(100)));

        List<Integer> listOrgans = new ArrayList<>();
        listOrgans.add(1);
        listOrgans.add(0);
        listOrgans.add(3);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(3);

        List<Disease> list = new ArrayList<>();
        Disease disease1 = new Disease(0, "Отруєння", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, true);
        list.add(disease1);
        return list;
    }
    private List<Question> loadQuestions()
    {
        List<Integer> symptoms = new ArrayList<>();
        symptoms.add(0);
        symptoms.add(1);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(0, "Як себе почуваєте?", symptoms));
        symptoms = new ArrayList<>();
        symptoms.add(2);
        questions.add(new Question(1, "Якi вiдчуття?", symptoms));
        return questions;
    }

    private List<Medicine> loadMedicines()
    {
        //заполнить из файла конфигурации
        List<Medicine> list = new ArrayList<>();
        Medicine medicine1 = new Medicine(0, "Панкреатин", 3, 100);
        List<Integer> listOrgans = new ArrayList<>();
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(3);
        listOrgans.add(-1);
        listOrgans.add(0);
        listOrgans.add(2);
        list.add(medicine1);
        medicine1.setParamOrgan(listOrgans);
        treat.put(medicine1.getType(),Diseases.get(0));
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
    
    

    
    public int getCurrentDay() {
        return CurrentDay;
    }

    public int getTotalDays() {
        return TotalDays;
    }

    public Player getAvatar() {
        return Avatar;
    }

    public List<Patient> getPatients() {
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

    public boolean getIsGame() {
        return isGame;
    }




    ////////////test load methods

}
