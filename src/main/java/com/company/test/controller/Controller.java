package com.company.test.controller;

import com.company.test.service.LabService;
import com.company.test.service.impl.ParserImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = {"/api"})
public class Controller {



    @Resource
    LabService labService;

    @GetMapping(value ={"/test"})
    public void test(){
        ParserImpl parser = new ParserImpl();
        parser.loginSber();
//        return labService.getStocks();
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
    public String login() {
        return labService.login();
    }

    @GetMapping(value ={"/logout"})
    public String logout() {
        return labService.logout();
    }

}
