package com.company.test.controller;

import com.company.test.model.StockDTO;
import com.company.test.service.Parser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(value = {"/api"})
public class Controller {
    Parser parser;

    @GetMapping(value ={"/test"})
    public List<StockDTO> test(){
        return parser.getStocks();
    }

    @GetMapping(value ={"/serialize"})
    public void serialize() {
//        parser.serializeStocks(parser.getStocks(parser.getData()));
        parser.serializeStocks(parser.getStocks(parser.getDataFromFile()));
    }
}
