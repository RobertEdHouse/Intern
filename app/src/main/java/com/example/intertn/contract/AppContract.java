package com.example.intertn.contract;

import androidx.fragment.app.Fragment;

public interface AppContract {
    void toInterviewScreen(Fragment target);

    void toBodyScreen(Fragment target);

    void cancel();
}
