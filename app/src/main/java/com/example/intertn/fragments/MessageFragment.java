package com.example.intertn.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;

public class MessageFragment extends BaseFragment {
    private static final String WORLD_CONTROLLER = "world_controller";
    private static final String DEBUG_TAG = "log";

    CountDownTimer countDownTimer;
    private Runnable runnable;
    private Handler handler;

    private WorldController worldController;

    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance(Bundle bundle) {
        MessageFragment fragment = new MessageFragment();
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
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hideUi(view);
        TextView mess=view.findViewById(R.id.textMessage);
        FrameLayout frame=view.findViewById(R.id.layoutMessage);

        if(worldController.isGame()){
            mess.setText("День "+worldController.getCurrentDay());
            frame.setOnClickListener(view1 -> {
                getAppContract().toInterviewScreen(this);
            });
        }
        else {
            mess.setText("Ви програли!");
            worldController.clear();
            frame.setOnClickListener(view1 -> {
                getAppContract().toMenuScreen();
            });
        }

    }


}