package com.example.intertn.controller;

import com.example.intertn.model.Question;
import com.example.intertn.model.World;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WorldController implements Serializable {
    private World world;

    public WorldController(World world){
        this.world=world;
    }
    public WorldController(){
        this.world=new World();
        world.init(0,5,1,1000);
    }

    public List<String> getQuestion(){
        List<String> listQuestionString=new ArrayList<>();
        for(Question q : world.getQuestions()){
            listQuestionString.add(q.getText());
        }
        return listQuestionString;
    }
}
