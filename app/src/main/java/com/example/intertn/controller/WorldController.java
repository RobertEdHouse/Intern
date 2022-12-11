package com.example.intertn.controller;

import android.content.Context;

import com.example.intertn.model.Dialog;
import com.example.intertn.model.Disease;
import com.example.intertn.model.ISaveLoad;
import com.example.intertn.model.Medicine;
import com.example.intertn.model.Organs;
import com.example.intertn.model.Patient;
import com.example.intertn.model.Question;
import com.example.intertn.model.SaveLoadClass;
import com.example.intertn.model.SexType;
import com.example.intertn.model.Symptom;
import com.example.intertn.model.SymptomManifest;
import com.example.intertn.model.World;
import com.example.intertn.model.WorldData;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class WorldController implements Serializable {
    private World world;
    private Patient currentPatient;
    private int countAction=3;

    public WorldController(){
        this.world=new World();
        setTest();
        world.init(0,10,1,1000);
        setTestPatients();
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
        return world.getAvatar().ask(world.getQuestions().get(id_question),currentPatient,world.getCurrentDay()).getText();
    }

    public List<String> getDialogs(){
        List<String>dialogsStr=new ArrayList<>();
        List<Dialog>dialogs=world.getAvatar().getDialogs(world.getCurrentDay(),currentPatient);
        for (Dialog d : dialogs){
            String str="[Питання]\n"+d.getQuestion()+"\n";
            str+="[Пацієнт]\n"+d.getAnswer()+"\n";
            dialogsStr.add(str);
        }
        return dialogsStr;
    }





    public void setCurrentPatient(Patient patient){
        currentPatient=patient;
    }

    public float getPatientTemperature(){
        return currentPatient.getTemperature();
    }

    public boolean nextPatient(){
        countAction=3;
        int currentIndex=world.getPatients().indexOf(currentPatient);
        if(currentIndex+1>=world.getPatients().size()){
            return false;
        }
        currentPatient=(world.getPatients().listIterator(currentIndex+1).next());

        return true;
    }
    public List<String> getCurrentDeadPatient(){
        List<String> patients=new ArrayList<>();
        for(Patient patient : world.getCurrentDeadPatients()){
            String str=patient.getFirstName()+" "+patient.getLastName()+" ";
            if(patient.getSex()==SexType.MALE)
                str+="загинув!\n";
            if(patient.getSex()==SexType.FEMALE)
                str+="загинула!\n";
            patients.add(str);
        }
        return patients;
    }

    public List<String> getCurrentHealedPatient(){
        List<String> patients=new ArrayList<>();
        for(Patient patient : world.getCurrentHealedPatients()){
            String str=patient.getFirstName()+" "+patient.getLastName()+" ";
            if(patient.getSex()==SexType.MALE)
                str+="вилікувався!\n";
            if(patient.getSex()==SexType.FEMALE)
                str+="вилікувалась!\n";
            patients.add(str);
        }
        return patients;
    }


    public void nextDay(){

        world.nextDay();
        if(!world.isGame())
            return;
        updateInfo();

    }

    public int getCurrentDay(){
        return world.getCurrentDay();
    }

    public boolean isGame() {
        return world.isGame();
    }

    public void saveGame(Context context){

        Runnable r= () -> {
            ISaveLoad save=new SaveLoadClass(context);
            save.SaveData(world);
        };
        Thread t=new Thread(r);
        t.start();
    }


    public void loadGame(Context context){
        Runnable r= () -> {
            ISaveLoad save=new SaveLoadClass(context);
            WorldData worldData=save.LoadData();
            if(world==null){
                world=new World();
                setTest();
            }
            world.LoadGame(worldData);
            updateInfo();
        };
        Thread t=new Thread(r);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateInfo(){

        setCurrentPatient(world.getPatients().get(0));
}

    private void setTest(){
        List<Integer> answers = new ArrayList<>();
        answers.add(0);
        answers.add(1);
        List<Symptom> symptoms = new ArrayList<>();
        symptoms.add(new Symptom(0, "Нездужання",answers));

        answers = new ArrayList<>();
        answers.add(2);
        answers.add(3);
        symptoms.add(new Symptom(1, "Рвота", answers));

        answers = new ArrayList<>();
        answers.add(4);
        answers.add(5);
        symptoms.add(new Symptom(2, "Діарея", answers));

        answers = new ArrayList<>();
        answers.add(6);
        answers.add(7);
        symptoms.add(new Symptom(3, "Зневоднення", answers));

        answers = new ArrayList<>();
        answers.add(8);
        answers.add(9);
        symptoms.add(new Symptom(4, "Гострий біль у кишечнику", answers));

        answers = new ArrayList<>();
        answers.add(10);
        answers.add(11);
        symptoms.add(new Symptom(5, "Нудота", answers));

        answers = new ArrayList<>();
        answers.add(12);
        answers.add(13);
        symptoms.add(new Symptom(6, "Біль у шлунку", answers));

        answers = new ArrayList<>();
        answers.add(14);
        answers.add(15);
        symptoms.add(new Symptom(7, "Гострий біль у шлунку", answers));

        answers = new ArrayList<>();
        answers.add(16);
        answers.add(17);
        symptoms.add(new Symptom(8, "Віддишка", answers));

        answers = new ArrayList<>();
        answers.add(18);
        answers.add(19);
        symptoms.add(new Symptom(9, "Кашель", answers));

        answers = new ArrayList<>();
        answers.add(20);
        answers.add(21);
        symptoms.add(new Symptom(10, "Слабкість", answers));

        answers = new ArrayList<>();
        answers.add(22);
        answers.add(23);
        symptoms.add(new Symptom(11, "Ядуха", answers));

        answers = new ArrayList<>();
        answers.add(24);
        answers.add(25);
        symptoms.add(new Symptom(12, "Інтоксикація", answers));

        answers = new ArrayList<>();
        answers.add(24);
        answers.add(25);
        symptoms.add(new Symptom(13, "Дезорієнтація", answers));

        answers = new ArrayList<>();
        answers.add(24);
        answers.add(25);
        symptoms.add(new Symptom(14, "Головний біль", answers));

        answers = new ArrayList<>();
        answers.add(24);
        answers.add(25);
        symptoms.add(new Symptom(15, "Сплутаність свідомості", answers));

        world.setTestSymptom(symptoms);



        List<Disease> list = new ArrayList<>();
        Random random = new Random();

        List<SymptomManifest> listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(0, 100));
        listSymptoms.add(new SymptomManifest(1, 80));
        listSymptoms.add(new SymptomManifest(2, 60));
        listSymptoms.add(new SymptomManifest(3, 40));
        listSymptoms.add(new SymptomManifest(4, 30));
        listSymptoms.add(new SymptomManifest(5, 20));

        List<Integer> listOrgans = new ArrayList<>();
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(2);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(1);

        Disease disease1 = new Disease(0, "Отруєння", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, true);
        list.add(disease1);

        listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(6, 100));
        listSymptoms.add(new SymptomManifest(1, 80));
        listSymptoms.add(new SymptomManifest(3, 60));
        listSymptoms.add(new SymptomManifest(7, 40));
        listSymptoms.add(new SymptomManifest(8, 30));

        listOrgans = new ArrayList<>();
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(2);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(0);

        disease1 = new Disease(1, "Виразка шлунку", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, false);
        list.add(disease1);

        listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(9, 100));
        listSymptoms.add(new SymptomManifest(10, 80));
        listSymptoms.add(new SymptomManifest(11, 60));

        listOrgans = new ArrayList<>();
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(2);
        listOrgans.add(0);

        disease1 = new Disease(2, "Пневмонія", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, true);
        list.add(disease1);

        listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(9, 100));
        listSymptoms.add(new SymptomManifest(10, 80));
        listSymptoms.add(new SymptomManifest(11, 60));
        listSymptoms.add(new SymptomManifest(12, 60));

        listOrgans = new ArrayList<>();
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(1);
        listOrgans.add(3);
        listOrgans.add(0);

        disease1 = new Disease(3, "Туберкульоз", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, true);
        list.add(disease1);

        listSymptoms = new ArrayList<>();
        listSymptoms.add(new SymptomManifest(1, 100));
        listSymptoms.add(new SymptomManifest(13, 80));
        listSymptoms.add(new SymptomManifest(14, 60));
        listSymptoms.add(new SymptomManifest(15, 60));

        listOrgans = new ArrayList<>();
        listOrgans.add(3);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(2);
        listOrgans.add(0);
        listOrgans.add(1);

        disease1 = new Disease(4, "Енцефаліт", 5, listSymptoms);
        disease1.setParamOrgan(listOrgans, true);
        list.add(disease1);

        world.setTestDisease(list);


        List<Integer> symptom = new ArrayList<>();
        symptom.add(0);

        List<Question> questions = new ArrayList<>();
        questions.add(new Question(0, "Як себе почуваєте?", symptom));
        symptom = new ArrayList<>();
        symptom.add(1);
        questions.add(new Question(1, "Як пройшла ніч?", symptom));
        world.setTestQuestion(questions);


        List<Medicine> listm = new ArrayList<>();
        Medicine medicine1 = new Medicine(0, "Сорбент", 5, 100,90);
        List<Integer> listOrgans2 = new ArrayList<>();
        listOrgans2.add(0);
        listOrgans2.add(0);
        listOrgans2.add(3);
        listOrgans2.add(0);
        listOrgans2.add(0);
        listOrgans2.add(1);
        listm.add(medicine1);
        medicine1.setParamOrgan(listOrgans2);
        List<Disease>treatDisease=new ArrayList<>();
        treatDisease.add(world.getDiseases().get(0));
        world.setTestTreat(medicine1.getType(),treatDisease);

        medicine1 = new Medicine(1, "Антибіотик", 5, 100,20);
        listOrgans2 = new ArrayList<>();
        listOrgans2.add(0);
        listOrgans2.add(0);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(1);
        listOrgans2.add(-1);
        listm.add(medicine1);
        medicine1.setParamOrgan(listOrgans2);
        treatDisease=new ArrayList<>();
        treatDisease.add(world.getDiseases().get(1));
        treatDisease.add(world.getDiseases().get(2));
        world.setTestTreat(medicine1.getType(),treatDisease);

        medicine1 = new Medicine(2, "Хіміотерапія", 5, 100,20);
        listOrgans2 = new ArrayList<>();
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listm.add(medicine1);
        medicine1.setParamOrgan(listOrgans2);
        treatDisease=new ArrayList<>();
        treatDisease.add(world.getDiseases().get(3));
        world.setTestTreat(medicine1.getType(),treatDisease);

        medicine1 = new Medicine(3, "Противірусні", 5, 100,20);
        listOrgans2 = new ArrayList<>();
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listOrgans2.add(-1);
        listm.add(medicine1);
        medicine1.setParamOrgan(listOrgans2);
        treatDisease=new ArrayList<>();
        treatDisease.add(world.getDiseases().get(4));
        world.setTestTreat(medicine1.getType(),treatDisease);

        medicine1 = new Medicine(4, "Вітаміни", 5, 100,20);
        listOrgans2 = new ArrayList<>();
        listOrgans2.add(1);
        listOrgans2.add(1);
        listOrgans2.add(1);
        listOrgans2.add(1);
        listOrgans2.add(1);
        listOrgans2.add(1);
        listm.add(medicine1);
        medicine1.setParamOrgan(listOrgans2);
        treatDisease=new ArrayList<>();
        treatDisease.add(new Disease(-1,"All",0,null));
        world.setTestTreat(medicine1.getType(),treatDisease);


        world.setTestMedicines(listm);



    }



    private void setTestPatients(){
        Random random = new Random();
        LinkedList<Patient> patients = new LinkedList<>();
        Patient newPatient = new Patient("Мартiн", "Септiм", SexType.MALE, 90, getCopyDisease(world.getDiseases().get(random.nextInt(world.getDiseases().size()))));
        Patient newPatient2 = new Patient("Алесса", "Сайлентхіловна", SexType.FEMALE, 20,getCopyDisease(world.getDiseases().get(random.nextInt(world.getDiseases().size()))));
        patients.add(newPatient);
        patients.add(newPatient2);
        world.setTestPatient(patients);
    }

    private Disease getCopyDisease(Disease d){
        Disease dis = new Disease(d.getId(),d.getName(),d.getMaxStage(),d.getListSymptom());
        List<Integer> listOrgans = new ArrayList<>();
        listOrgans.add(1);
        listOrgans.add(0);
        listOrgans.add(3);
        listOrgans.add(0);
        listOrgans.add(0);
        listOrgans.add(3);
        dis.setParamOrgan(listOrgans,true);
        return dis;
    }

    public SexType getCurrentPatientSex(){
        return currentPatient.getSex();
    }

    public void clear() {
        world=null;
    }

    public int getOrganState(Organs organ){
        switch (organ){
            case Brain:
                return currentPatient.getBrain();
            case Heart:
                return currentPatient.getHeart();
            case Liver:
                return currentPatient.getLiver();
            case Lungs:
                return currentPatient.getLungs();
            case Stomach:
                return currentPatient.getStomach();
            case Intestines:
                return currentPatient.getIntestines();
        }
        return 0;
    }

    public List<String> getMedicines(Organs organ){
        List<String> medStr=new ArrayList<>();
        for (Medicine m:world.getAvatar().getMedicines()) {
            if(m.Brain!=0&&organ.equals(Organs.Brain))
                medStr.add(m.getType());
            if(m.Stomach!=0&&organ.equals(Organs.Stomach))
                medStr.add(m.getType());
            if(m.Heart!=0&&organ.equals(Organs.Heart))
                medStr.add(m.getType());
            if(m.Intestines!=0&&organ.equals(Organs.Intestines))
                medStr.add(m.getType());
            if(m.Lungs!=0&&organ.equals(Organs.Lungs))
                medStr.add(m.getType());
            if(m.Liver!=0&&organ.equals(Organs.Liver))
                medStr.add(m.getType());
        }
        return medStr;

    }
    public int getMedicineCount(String type){
        return world.getAvatar().getMedicineCount(type);
    }

    public void treatPatient(String medicineType){
        if(countAction==0)
            return;
        world.getAvatar().giveMedicine(currentPatient,medicineType);
        countAction--;
    }

    public String getHistory(){
        String str="";

        str+="Вилікувано: "+world.getHealedPatients().size()+"\n";
        str+="Загинуло: "+world.getDeadPatients().size()+"\n";
        str+="Залишилось хворими: "+world.getPatients().size();
        return str;
    }
    public int getCountAction(){
        return countAction;
    }

    public String getPatientState(){
        return String.valueOf(currentPatient.getStatePercent());
    }
}
