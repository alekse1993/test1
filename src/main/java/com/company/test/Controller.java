package com.company.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping(value = {"/api"})
public class Controller {
    @GetMapping(value ={"/test"})
    @ResponseBody
    public List<Stock> test(){
        Parser parser = new Parser();
        String data = "";
        List<Stock> stocks;
        stocks = parser.getStocks();
        parser.groupStocks(stocks);
//        try{
//            stocks = parser.getStocks(parser.getData());
//        }
//        catch(Exception e){
//            stocks = parser.getStocks(parser.getData());
//        }

        return stocks;
    }

    @GetMapping(value ={"/serialize"})
    @ResponseBody
    public void serialize() {
        Parser parser = new Parser();
        parser.serializeStocks(parser.getStocks(parser.getData()));
    }
}
