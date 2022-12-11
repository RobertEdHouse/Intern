package com.example.intertn.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;
import com.example.intertn.model.Organs;
import com.example.intertn.model.SexType;

import java.util.List;

public class BodyFragment extends BaseFragment {
    private static final String WORLD_CONTROLLER = "world_controller";
    private static final String ORGAN = "organ";


    private WorldController worldController;
    private TextView dialogsText;
    public BodyFragment() {
    }

    public static BodyFragment newInstance(Bundle bundle) {
        BodyFragment fragment = new BodyFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORLD_CONTROLLER, bundle.getSerializable(WORLD_CONTROLLER));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            worldController=(WorldController) getArguments().getSerializable(WORLD_CONTROLLER);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_body, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hideUi(view);
        ImageView imagePatient=view.findViewById(R.id.imagePatient);
        if(worldController.getCurrentPatientSex()== SexType.MALE) {
            imagePatient.setImageResource(R.drawable.icon_male_1);
        } else if(worldController.getCurrentPatientSex()== SexType.FEMALE)
            imagePatient.setImageResource(R.drawable.icon_female_3);

        view.findViewById(R.id.buttonToDialog).setOnClickListener(v -> getAppContract().toInterviewScreen(this));
        view.findViewById(R.id.buttonToNextPatient).setOnClickListener(v -> {
            if(!worldController.nextPatient()){
                worldController.nextDay();
                getAppContract().toEndScreen(this);
            }
            else
                getAppContract().toInterviewScreen(this);
        });

        view.findViewById(R.id.buttonBrain).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Brain);
            getAppContract().toOrganScreen(this);
        });
        view.findViewById(R.id.buttonHeart).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Heart);
            getAppContract().toOrganScreen(this);
        });
        view.findViewById(R.id.buttonStomach).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Stomach);
            getAppContract().toOrganScreen(this);
        });
        view.findViewById(R.id.buttonIntestines).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Intestines);
            getAppContract().toOrganScreen(this);
        });
        view.findViewById(R.id.buttonLiver).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Liver);
            getAppContract().toOrganScreen(this);
        });
        view.findViewById(R.id.buttonLungs).setOnClickListener(v -> {
            Bundle args = getArguments();
            assert args != null;
            args.putSerializable(ORGAN, Organs.Lungs);
            getAppContract().toOrganScreen(this);
        });

        TextView patientText=view.findViewById(R.id.textTemperature);
        patientText.setText(worldController.getPatientTemperature() +"°C");
        TextView patientStateText=view.findViewById(R.id.textStatePatient);
        patientStateText.setText(worldController.getPatientState() +"%");
        dialogsText=view.findViewById(R.id.textDialogs);
        setDialogsText();
    }

    @SuppressLint("SetTextI18n")
    private void setDialogsText(){
        List<String> listDialogs=worldController.getDialogs();
        for(String d : listDialogs){
            dialogsText.setText(d+"\n"+dialogsText.getText());
        }
        if(listDialogs.isEmpty())
            dialogsText.setText("...");
    }

}