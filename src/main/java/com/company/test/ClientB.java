package com.company.test;

public class ClientB implements IMailSender {

    @Override
    public void sendMessage() {
        System.out.println("Send msg via VK");
    }
}