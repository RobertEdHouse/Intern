package com.example.intertn;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.intertn.fragments.InterviewFragment;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {

            launchFragment(null, new InterviewFragment());
        }
    }

    private void launchFragment(@Nullable Fragment target,
                                Fragment fragment) {
        if (target != null) {
            fragment.setTargetFragment(target, 0);
        }
        String tag = UUID.randomUUID().toString();
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }
}