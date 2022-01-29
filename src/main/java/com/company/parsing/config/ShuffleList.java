package com.company.parsing.config;

import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public  class ShuffleList {
    @Value("${app.wildberries.first.id}")
    Integer firstId;

    @Value("${app.wildberries.last.id}")
    Integer lastId;

    public ShuffleList() {
    }


    public ArrayList<Integer> getShuffledList(){
        ArrayList<Integer>  idsList = new ArrayList<>();
        for(int val = this.firstId;val<=this.lastId;val++){
            idsList.add(val);
        }
        Collections.shuffle(idsList,new Random());
        return idsList;
    }
}
