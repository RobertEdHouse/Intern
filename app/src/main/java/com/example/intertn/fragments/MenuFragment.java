package com.example.intertn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;


public class MenuFragment extends BaseFragment {

    private WorldController worldController;
    private static final String WORLD_CONTROLLER = "world_controller";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MenuFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //buttonNewGame
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideUi(view);

        view.findViewById(R.id.buttonNewGame).setOnClickListener(v -> {
            worldController=startNewGame();
            Bundle bundle = new Bundle();
            bundle.putSerializable(WORLD_CONTROLLER,worldController);
            this.setArguments(bundle);
            getAppContract().toMessageScreen(this);
        });
        view.findViewById(R.id.buttonLoadGame).setOnClickListener(v -> {
            worldController=new WorldController();
            Bundle bundle = new Bundle();
            bundle.putSerializable(WORLD_CONTROLLER,worldController);
            this.setArguments(bundle);
            worldController.loadGame(getContext());

            getAppContract().toMessageScreen(this);
        });
    }

    private WorldController startNewGame(){
        WorldController worldController=new WorldController();
        return worldController;
    }
}