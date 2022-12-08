package com.example.intertn.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.intertn.service.LoadSaveService;

import java.io.Serializable;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EndFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EndFragment extends BaseFragment implements Serializable {

    private static final String WORLD_CONTROLLER = "world_controller";
    private static final String DEBUG_TAG = "log";
    public transient Context mContext;

    private transient WorldController worldController;

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

        if (worldController!= null){
            launchService(LoadSaveService.ACTION_SAVE_GAME);}


    }
    private void launchService(String action) {
        mContext=getContext();
        Intent intent = new Intent(this.getActivity(), LoadSaveService.class);
        intent.setAction(action);
        intent.putExtra("fragment",  this);
        intent.putExtra(WORLD_CONTROLLER,  worldController);
        requireActivity().startService(intent);
    }
}