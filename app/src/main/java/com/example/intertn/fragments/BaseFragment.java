package com.example.intertn.fragments;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.intertn.contract.AppContract;

public class BaseFragment extends Fragment {
    private AppContract appContract;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.appContract = (AppContract) context;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.appContract = null;
    }
    final AppContract getAppContract() {
        return appContract;
    }

    public void hideUi(View view){
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        view.setSystemUiVisibility(uiOptions);
    }
}
