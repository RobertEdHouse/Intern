package com.example.intertn.fragments;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.intertn.R;
import com.example.intertn.controller.WorldController;
import com.example.intertn.model.Organs;

import java.util.ArrayList;
import java.util.List;


public class OrganFragment extends BaseFragment {

    private WorldController worldController;
    private Organs organ;

    private static final String ORGAN = "organ";
    private static final String WORLD_CONTROLLER = "world_controller";


    public OrganFragment() {
    }

    public static OrganFragment newInstance(Bundle bundle) {
        OrganFragment fragment = new OrganFragment();
        Bundle args = new Bundle();
        args.putSerializable(WORLD_CONTROLLER, bundle.getSerializable(WORLD_CONTROLLER));
        args.putSerializable(ORGAN, bundle.getSerializable(ORGAN));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            worldController=(WorldController) getArguments().getSerializable(WORLD_CONTROLLER);
            organ = (Organs) getArguments().getSerializable(ORGAN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_organ, container, false);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        hideUi(view);
        ImageView imageOrgan=view.findViewById(R.id.imageOrgan);
        TextView textOrgan=view.findViewById(R.id.textNameOrgan);
        switch (organ){
            case Brain:
                textOrgan.setText("Мозок");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
            case Heart:
                textOrgan.setText("Седце");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
            case Liver:
                textOrgan.setText("Печінка");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
            case Lungs:
                textOrgan.setText("Легені");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
            case Stomach:
                textOrgan.setText("Шлунок");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
            case Intestines:
                textOrgan.setText("Кишечник");
                imageOrgan.setImageResource(R.drawable.brain);
                break;
        }
        List<ImageView> points=new ArrayList<>();
        points.add(view.findViewById(R.id.imagePoint5));
        points.add(view.findViewById(R.id.imagePoint4));
        points.add(view.findViewById(R.id.imagePoint3));
        points.add(view.findViewById(R.id.imagePoint2));
        points.add(view.findViewById(R.id.imagePoint1));

        int state = worldController.getOrganState(organ);
        for(int i=0;i<state/2;i++){
            points.get(i).setImageResource(R.drawable.circle_full);
        }
        for(int i=state/2;i<state/2+state%2;i++){
            points.get(i).setImageResource(R.drawable.circle_half);
        }
        for(int i=state/2+state%2;i<5;i++){
            points.get(i).setImageResource(R.drawable.circle_empty);
        }
        view.findViewById(R.id.buttonBack).setOnClickListener(v -> getAppContract().toBodyScreen(this));

        setMedicinesList(view);
    }

    @SuppressLint("SetTextI18n")
    private void setMedicinesList(View view){

        LinearLayout l=view.findViewById(R.id.scrollLayoutMedicines);
        List<String>medicines=worldController.getMedicines(organ);
        l.removeAllViews();
        for(String m : medicines){
            Button med=new Button(getActivity());
            med.setText(m + " " + worldController.getMedicineCount(m));
            med.setBackgroundResource(R.drawable.button_shape_selector);
            med.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
            med.setAllCaps(false);
            med.setTextColor(Color.parseColor("#2bc051"));
            med.setTextSize(24);
            med.setOnClickListener(view1 -> {
                worldController.treatPatient(m);
                setMedicinesList(view);
            });
            l.addView(med);
        }
        if(medicines.isEmpty()){
            TextView med=new TextView(getActivity());
            med.setText("Ліків немає");
            med.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            med.setTextColor(Color.parseColor("#2bc051"));
            med.setTextSize(24);
            l.addView(med);
        }
    }
}