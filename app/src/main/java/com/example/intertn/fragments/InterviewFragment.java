package com.example.intertn.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;
import com.example.intertn.model.SexType;

import java.util.ArrayList;
import java.util.List;


public class InterviewFragment extends BaseFragment {

    private static final String WORLD_CONTROLLER = "world_controller";

    private TextView patientText;

    private WorldController worldController;

    public static InterviewFragment newInstance(Bundle bundle) {
        InterviewFragment fragment = new InterviewFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORLD_CONTROLLER, bundle.getSerializable(WORLD_CONTROLLER));
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState==null) {
            assert getArguments() != null;
            worldController = (WorldController) getArguments().getSerializable(WORLD_CONTROLLER);
        }
        else
            worldController = (WorldController) savedInstanceState.getSerializable(WORLD_CONTROLLER);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(
                R.layout.fragment_interview,
                container,
                false
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideUi(view);

        patientText=view.findViewById(R.id.textPatient);
        patientText.setText(worldController.getStartAnswer());
        ImageView imagePatient=view.findViewById(R.id.imagePatient);
        if(worldController.getCurrentPatientSex()== SexType.MALE) {
            imagePatient.setImageResource(R.drawable.icon_male_1);
        } else if(worldController.getCurrentPatientSex()== SexType.FEMALE)
            imagePatient.setImageResource(R.drawable.icon_female_3);
        view.findViewById(R.id.buttonTreat).setOnClickListener(v -> getAppContract().toBodyScreen(this));

        List<Button> questionButtons=new ArrayList<>();
        questionButtons.add(view.findViewById(R.id.buttonQuestion1));
        questionButtons.get(0).setOnClickListener(v-> patientText.setText(worldController.getAnswer(0)+"\n"));
        questionButtons.add(view.findViewById(R.id.buttonQuestion2));
        questionButtons.get(1).setOnClickListener(v-> patientText.setText(worldController.getAnswer(1)+"\n"));

        setQuestionText(questionButtons);
    }


    private void setQuestionText(List<Button> questionButtons){
        List<String> questions=worldController.getQuestion();
        for(int i=0; i<questions.size()&&i<questionButtons.size();i++){
            questionButtons.get(i).setText(questions.get(i));
        }
    }
}