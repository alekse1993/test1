package com.company.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class Controller2 {
    @GetMapping(value ={"/test2"})
    public String test2(Model model){

        return "sms";
    }

    @GetMapping(value ={"/submit"})
    public String getCode(Model model, @RequestParam("name") String name){
        log.info(name);
        model.addAttribute("text",name);
        return "sms";
    }
}
