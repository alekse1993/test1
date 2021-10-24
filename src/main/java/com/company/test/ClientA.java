package com.company.test;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ClientA implements IMailSender {

    @Override
    public void sendMessage() {
        System.out.println("Send msg via Email");
    }
}
