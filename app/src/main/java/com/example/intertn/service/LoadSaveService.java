package com.example.intertn.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.intertn.BuildConfig;
import com.example.intertn.MainActivity;
import com.example.intertn.controller.WorldController;
import com.example.intertn.model.WorldData;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;

public class LoadSaveService extends IntentService {
    public static final String ACTION_SAVE_GAME =
            BuildConfig.APPLICATION_ID + ".ACTION_SAVE_HISTORY";
    public static final String ACTION_LOAD_GAME =
            BuildConfig.APPLICATION_ID + ".ACTION_LOAD_HISTORY";
    public LoadSaveService(String name) {
        super(name);
    }
    public LoadSaveService() {
        super("default_service_name");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent == null) return;
        String action = intent.getAction();
        if (action == null) return;
        try {
            handleAction(action, intent);
        } catch (InterruptedException e) {
            //
        }
    }

    private void handleAction(String action, Intent intent)
            throws InterruptedException {
        switch (action) {
            case ACTION_SAVE_GAME:
                save(intent);
                break;
            case ACTION_LOAD_GAME:
                //readFile();
                break;
            default:
                throw new RuntimeException("Unknown action!");
        }
    }
    private void save(Intent intent){
        MainActivity fragment = (MainActivity)intent.getSerializableExtra("fragment");
        Context context=fragment.getBaseContext();
        WorldController worldController=(WorldController) intent.getSerializableExtra("world_controller");
        String fileName= null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            fileName = FileSystems.getDefault().getPath(".") + "\\Game";
        }
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            WorldData worldData = worldController.getWorldFata();
            os.writeObject(worldData);
            os.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("tag","Save Game! Eeeeee");
    }


}
