package com.example.intertn.model;

import java.io.Serializable;
import java.util.ArrayList;
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


        CurrentState = State.NORMAL;
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
        Temperature -= ((float)Immunity / 100) * ((rand.nextInt(3 - 1) + 1) );
        Temperature = ((float)Math.ceil(Temperature * 10))/10;
        if (Temperature < 36.6f)
            Temperature = 36.6f;
    }

    public void takeMedicine(Medicine medicine)
    {
        for (Medicine m:Medicines) {
            if(m.getId()==medicine.getId()){
                m.add();
                return;
            }
        }
        Medicines.add(medicine);
    }


    public void nextDay(List<Symptom> symptoms, Map<String, List<Disease>> treat)
    {
        //?????????????????? ??????????????????
        //???????????????? ?????????????????? ????????????????
        List<Disease> activeDiseases = getDiseases();
        List<Medicine> medicines = getMedicines();
        if(Medicines.size()>0){
            for (Disease dis : Diseases)
            {
                boolean isTreat=false;
                for (Medicine med : medicines)
                {
                    if (treat.get(med.getType())!=null)
                    {
                        List<Disease>diseaseList=treat.get(med.getType());
                        for (Disease disis:diseaseList) {

                            if(disis.getId()==dis.getId()) {
                                int countMedicine=med.getCount();
                                for (int i = 0; i < countMedicine; i++) {
                                    if (dis.isTemperature() == true)
                                        lowerTemperature();
                                    med.treatOrgans(this);
                                    med.used();
                                    if (med.getCount() == 0)
                                        Medicines.remove(med);
                                    dis.downStage(Immunity,med.getPower());
                                    isTreat=true;
                                }
                            }
                            else if(disis.getId()==-1){
                                int countMedicine=med.getCount();
                                for (int i = 0; i < countMedicine; i++) {
                                    med.treatOrgans(this);
                                    med.used();
                                    if (med.getCount() == 0)
                                        Medicines.remove(med);
                                }
                            }
                        }
                    }
                }
                if(isTreat){
                    activeDiseases.remove(dis);
                    if (activeDiseases.size() == 0)
                        break;
                }
            }
        }

        List<Disease> diseases = Diseases;
        for (Disease dis : Diseases)
        {
            if (dis.getStagePercent() <= 0)
            {
                diseases.remove(dis);
                changeState();
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
                dis.raiseStage(Immunity);
                if(Immunity>50)
                    Immunity-=Immunity/((new Random()).nextInt(10-1)+1);
                changeState();
            }

        }
        updateSymptoms(symptoms);

        //???????????????? ?????????????????? ??????????????
        //???????????????? ?????????????????????? ?? ?????????????????????? ???? ?????????????????? ??????????????
    }
    private List<Disease> getDiseases(){
        List<Disease> diseases = new ArrayList<>();
        for(Disease d:Diseases)
            diseases.add(d);
        return diseases;
    }

    private List<Medicine> getMedicines(){
        List<Medicine> medicines = new ArrayList<>();
        for(Medicine d:Medicines)
            medicines.add(d);
        return medicines;
    }



    private void changeState()
    {
        if (Brain == 0 || Heart == 0 ||
                Liver == 0 || Intestines == 0 ||
                Lungs == 0 || Stomach == 0 || Temperature>=42){
            this.CurrentState = State.DEAD;
            return;
        }
        for(Disease dis : Diseases)
        {
            if(dis.getStagePercent()>=100)
                this.CurrentState = State.DEAD;
            else if(dis.getStagePercent()<=0)
                this.CurrentState = State.NORMAL;
        }
        if(Diseases.isEmpty())
            this.CurrentState=State.NORMAL;
        else{
            this.CurrentState=State.SICK;
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


        //???? ???????????? ?????????????? ???????????????? ????????????????
    }

    public Answer answer(Question question)
    {
        if(question==null){
            return new Answer(-1,getStandardAnswer());
        }
        List<Symptom> commonSymptoms = new ArrayList<>();
//        for (int code : question.getSymptomCodes())
//        {
//            for (Symptom symptom : Symptoms)
//            {
//                if (code == symptom.getId())
//                    commonSymptoms.add(symptom);
//            }
//        }
        Random rand = new Random();
        Symptom symptomRandom = Symptoms.get(rand.nextInt(Symptoms.size()));
        return symptomRandom.getAnswer(question.getSymptomCodes().get(0));
    }
    private String getStandardAnswer(){
        return "?????????????? ??????\n";

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

    public int getStatePercent(){
        int state=Diseases.size()*100;
        for (Disease d:Diseases) {
            state-=d.getStagePercent();
        }
        return state/Diseases.size();
    }
}
