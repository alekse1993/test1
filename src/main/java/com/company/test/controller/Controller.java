package com.company.test.controller;

import com.company.test.service.LabService;
import com.company.test.service.impl.ParserImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@org.springframework.stereotype.Controller
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
    public String addAll(Model model){
        String text = labService.addStocks();
        model.addAttribute("text", text);
        return "sms";
    }

    @GetMapping(value ={"/clear/stocks"})
    public String clearStocks(Model model){
        String text =  labService.clearStocks();
        model.addAttribute("text", text);
        return "sms";
    }

    @GetMapping(value ={"/clear/bonds"})
    public String clearBonds(Model model){
        String text =  labService.clearBonds();
        model.addAttribute("text", text);
        return "sms";
    }

    @GetMapping(value ={"/clear/all"})
    public String clearAll(Model model){
        String stocksMsg = labService.clearStocks();
        String bondsMsg = labService.clearBonds();
        String text =  stocksMsg + "\n" + bondsMsg;
        model.addAttribute("text", text);
        return "sms";
    }

    @GetMapping(value ={"/serialize"})
    public String serialize() {
        return labService.serialize();
    }

    @GetMapping(value ={"/login"})
    public String login(Model model) {
        String text =  labService.login();
        model.addAttribute("text", text);
        return "sms";
    }

    @GetMapping(value ={"/logout"})
    public String logout(Model model) {
        String text =  labService.logout();
        model.addAttribute("text", text);
        return "sms";
    }

}
