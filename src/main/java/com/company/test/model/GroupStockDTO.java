package com.company.test.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class GroupStockDTO {
    private String secCode;
    @Setter(AccessLevel.NONE)
    private Integer count;
    private Double cost;
    private List<StockDTO> stockDTOS;

    public Integer getCount(){
        return this.stockDTOS.size();
    }

}


