package com.company;

import com.company.test.ClientA;
import com.company.test.ClientB;
import com.company.test.Configuration;
import com.company.test.IMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    @Autowired
    static IMailSender clientA;
    public static void main(String[] args) {

////        ApplicContext context = new ApplicContext("kdkjf");
////        context.getBean("adf");
//
//        SpringApplication.run(Main.class, args);
//        IMailSender clientA = new ClientA();
//        IMailSender clientB = new ClientB();

        Configuration config = new Configuration(clientA);
//        Configuration config2 = new Configuration(clientB);

        config.process();

    }
}
