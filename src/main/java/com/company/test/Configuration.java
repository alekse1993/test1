package com.company.test;

public class Configuration {
    IMailSender sender;

    public Configuration(IMailSender sender) {
        this.sender = sender;
    }
    public void process(){
        System.out.println("Massage Configuration is:\n");
        sender.sendMessage();
    }

}
