package com.company.test.controller;

import com.company.test.model.Session;
import com.company.test.model.SessionImpl;
import com.company.test.model.StockDTO;
import com.company.test.service.ClientConfiguration;
import com.company.test.service.LabService;
import com.company.test.service.Parser;
import javax.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = {"/api"})
public class Controller {

    @Resource
    LabService labService;

    @GetMapping(value ={"/test"})
    public List<StockDTO> test(){
        return labService.getStocks();
    }

    @GetMapping(value ={"/add/all"})
    public String addAll(){
        return labService.addStocks();
    }

    @GetMapping(value ={"/clear/stocks"})
    public String clearStocks(){
        return labService.clearStocks();
    }

    @GetMapping(value ={"/clear/bonds"})
    public String clearBonds(){
        return labService.clearBonds();
    }

    @GetMapping(value ={"/clear/all"})
    public String clearAll(){
        String stocksMsg = labService.clearStocks();
        String bondsMsg = labService.clearBonds();
        return stocksMsg + "\n" + bondsMsg;
    }

    @GetMapping(value ={"/serialize"})
    public String serialize() {
        return labService.serialize();
    }

    @GetMapping(value ={"/login"})
    public String start() {
        return labService.start();
    }


}
