package com.company.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@org.springframework.stereotype.Controller
@RequestMapping(value = {"/api"})
public class Controller {
    @GetMapping(value ={"/test"})
    @ResponseBody
    public String test(){
        Parser parser = new Parser();
        String data = "";
        parser.stockFromJson(Constants.jsonObject);

//        try{
//            data = parser.getData().toString();
//        }
//        catch(Exception e){
//            data = e.getMessage();
//        }
        return data;
    }

}
