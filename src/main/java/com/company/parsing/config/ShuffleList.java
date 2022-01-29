package com.company.parsing.config;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public  class ShuffleList {
    @Value("${app.wildberries.first.id}")
    Integer firstId;

    @Value("${app.wildberries.last.id}")
    Integer lastId;

    public ShuffleList() {
    }

    @Bean
    public ArrayList<Integer> getShuffledList(){
        ArrayList<Integer>  idsList = new ArrayList<>();
        for(int val = this.firstId;val<=this.lastId;val++){
            idsList.add(val);
        }
        Collections.shuffle(idsList,new Random());
        return idsList;
    }
}
