package com.example.intertn.contract;

import androidx.fragment.app.Fragment;

public interface AppContract {
    void toInterviewScreen(Fragment target);
    void toBodyScreen(Fragment target);
    void toOrganScreen(Fragment target);
    void toMessageScreen(Fragment target);
    void toEndScreen(Fragment target);
    void toMenuScreen();
    void cancel();
}
