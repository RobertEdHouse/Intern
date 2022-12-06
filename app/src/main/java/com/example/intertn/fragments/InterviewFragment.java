package com.example.intertn.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;

import java.util.ArrayList;
import java.util.List;


public class InterviewFragment extends BaseFragment {

    private static final String WORLD_CONTROLLER = "world_controller";
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
        if(savedInstanceState==null)
            worldController=(WorldController) getArguments().getSerializable(WORLD_CONTROLLER);
        else
            worldController=(WorldController) savedInstanceState.getSerializable(WORLD_CONTROLLER);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideUi(view);
        view.findViewById(R.id.buttonTreat).setOnClickListener(v -> {
            getAppContract().toBodyScreen(this);
        });

        List<Button> questionButtons=new ArrayList<>();
        questionButtons.add(view.findViewById(R.id.buttonQuestion1));
        questionButtons.add(view.findViewById(R.id.buttonQuestion2));
        questionButtons.add(view.findViewById(R.id.buttonQuestion3));
        questionButtons.add(view.findViewById(R.id.buttonQuestion4));

        setQuestionText(questionButtons);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void setQuestionText(List<Button> questionButtons){
        List<String> questions=worldController.getQuestion();
        for(int i=0; i<questions.size()&&i<questionButtons.size();i++){
            questionButtons.get(i).setText(questions.get(i));
        }
    }
}