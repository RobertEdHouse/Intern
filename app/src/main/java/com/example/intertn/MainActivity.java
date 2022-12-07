package com.example.intertn;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.intertn.contract.AppContract;
import com.example.intertn.fragments.BodyFragment;
import com.example.intertn.fragments.EndFragment;
import com.example.intertn.fragments.InterviewFragment;
import com.example.intertn.fragments.MenuFragment;
import com.example.intertn.fragments.MessageFragment;

import java.util.UUID;

public class MainActivity extends AppCompatActivity implements AppContract {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            launchFragment(new MenuFragment());
        }
    }



    @Override
    public void toInterviewScreen(Fragment target) {
        Bundle bundle = null;
        if(target!=null)
            bundle = target.getArguments();
        launchFragment(InterviewFragment.newInstance(bundle));

    }

    @Override
    public void toBodyScreen(Fragment target) {
        Bundle bundle = null;
        if(target!=null)
            bundle = target.getArguments();
        launchFragment(BodyFragment.newInstance(bundle));
    }

    @Override
    public void toMessageScreen(Fragment target) {
        Bundle bundle = null;
        if(target!=null)
            bundle = target.getArguments();
        launchFragment(MessageFragment.newInstance(bundle));
    }

    @Override
    public void toEndScreen(Fragment target) {
        Bundle bundle = null;
        if(target!=null)
            bundle = target.getArguments();
        launchFragment(EndFragment.newInstance(bundle));
    }

    @Override
    public void toMenuScreen() {
        launchFragment(new MenuFragment());
    }

    @Override
    public void cancel() {

    }

    private void launchFragment(Fragment fragment){
        String tag = UUID.randomUUID().toString();
        for (Fragment fr : getSupportFragmentManager().getFragments()) {
            getSupportFragmentManager().beginTransaction().remove(fr).commit();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment, tag)
                .commit();
    }
}