package com.company.test.controller;

import com.company.test.model.StockDTO;
import com.company.test.service.ClientConfiguration;
import com.company.test.service.Parser;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = {"/api"})
public class Controller {
    Parser parser;

    @GetMapping(value ={"/test"})
    public List<StockDTO> test(){
        return parser.getStocks();
    }


    @GetMapping(value ={"/test2"})
    public String test2(){
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        return clientConfiguration.getClient();
    }

    @GetMapping(value ={"/serialize"})
    public void serialize() {
//        parser.serializeStocks(parser.getStocks(parser.getData()));
        parser.serializeStocks(parser.getStocks(parser.getDataFromFile()));
    }
}
