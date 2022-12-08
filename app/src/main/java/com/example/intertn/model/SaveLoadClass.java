package com.example.intertn.model;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;

public class SaveLoadClass implements ISaveLoad {
    private Context context;
    public SaveLoadClass(Context context){
        this.context=context;
    }
    public void SaveData(World world)
    {

        String fileName= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fileName = FileSystems.getDefault().getPath(".") + "\\Game";
        }
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            WorldData worldData = new WorldData(
                    world.getCurrentDay(), world.getTotalDays(), world.getAvatar(), world.getPatients(), world.getDeadPatients());
            os.writeObject(worldData);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public WorldData LoadData()
    {
        WorldData data=null;
        String fileName= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fileName = FileSystems.getDefault().getPath(".") + "\\Game";
        }
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            ObjectInputStream is = new ObjectInputStream(fis);
            data=(WorldData) is.readObject();
            is.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }
}
