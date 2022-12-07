package com.example.intertn.fragments;

import android.annotation.SuppressLint;
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
import androidx.fragment.app.Fragment;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndFragment extends BaseFragment {

    private static final String WORLD_CONTROLLER = "world_controller";
    private static final String DEBUG_TAG = "log";

    CountDownTimer countDownTimer;
    private Runnable runnable;
    private Handler handler;

    private WorldController worldController;

    public EndFragment() {
        // Required empty public constructor
    }

    public static EndFragment newInstance(Bundle bundle) {
        EndFragment fragment = new EndFragment();
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
        return inflater.inflate(R.layout.fragment_end, container, false);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hideUi(view);
        TextView mess=view.findViewById(R.id.textEndMessage);
        FrameLayout frame=view.findViewById(R.id.layoutEndMessage);

        List<String> deadPatient=worldController.getCurrentDeadPatient();
        for(String s : deadPatient){
            mess.setText(mess.getText()+s);
        }
        frame.setOnClickListener(view1 -> {
            getAppContract().toMessageScreen(this);
        });

    }
}