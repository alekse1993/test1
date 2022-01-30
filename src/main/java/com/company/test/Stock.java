package com.company.test;

import lombok.Data;

import java.io.Serializable;

@Data
public class Stock implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    private String market;
    private String secCode;
    private String currentCost;

}


