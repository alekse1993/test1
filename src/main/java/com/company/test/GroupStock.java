package com.company.test;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GroupStock {
    private String secCode;
    @Setter(AccessLevel.NONE)
    private Integer count;
    private Double cost;
    private List<Stock> stocks;

    public Integer getCount(){
        return this.stocks.size();
    }

}


