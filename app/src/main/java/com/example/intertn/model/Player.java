package com.example.intertn.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int Karma;
    private int Money;
    private List<Medicine> Medicines;
    private List<Dialog> Dialogs;

    public Player(List<Medicine> medicines, int money) {
        Karma = 2;
        Money = money;
        Medicines = medicines;
        Dialogs = new ArrayList<>();
    }

    public boolean buyMedicine(Medicine medicine)
    {
        if (medicine.getPrice() > this.Money)
        {
            return false;
        }
        this.Money -= medicine.getPrice();
        for(Medicine m : Medicines)
        {
            if (m.getId()==medicine.getId())
            {
                m.add(medicine.getCount());
            }
        }
        Medicines.add(medicine);
        return true;
    }
    public void giveMedicine(Patient patient,Medicine medicine)
    {
        patient.takeMedicine(medicine);
        medicine.used();
    }

    public Answer ask(Question question,Patient patient,int Day)
    {
        Answer answer = patient.answer(question);
        Dialog newDialog = new Dialog(patient, question.getText(), answer.getText(), Day);
        addDialog(newDialog);
        return answer;
    }

    public Dialog getDialog(Patient patient)
    {
        for(Dialog dialog : Dialogs)
        {
            if (dialog.getPatient().equals(patient))
            {
                return dialog;
            }
        }
        return null;
    }
    public Dialog getDialog(int dialogId)
    {
        return Dialogs.get(dialogId);
    }

    public List<Dialog> getDialogs(Patient patient)
    {
        List<Dialog> dialogs = new ArrayList<>();
        for (Dialog dialog : Dialogs)
        {
            if (dialog.getPatient().equals(patient))
            {
                dialogs.add(dialog);
            }
        }
        return dialogs;
    }
    public List<Dialog> getDialogs(int Day,Patient patient)
    {
        List<Dialog> dialogs = new ArrayList<>();
        for (Dialog dialog : Dialogs)
        {
            if (dialog.getPatient().equals(patient)&&dialog.getDay()==Day)
            {
                dialogs.add(dialog);
            }
        }
        return dialogs;
    }

    public List<Dialog> getDialogs() {
        return Dialogs;
    }

    public void addDialog(Dialog dialog)
    {
        Dialogs.add(dialog);
    }

    public int getKarma() {
        return Karma;
    }

    public int getMoney() {
        return Money;
    }

    public List<Medicine> getMedicines() {
        return Medicines;
    }
}
