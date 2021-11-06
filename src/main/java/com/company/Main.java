package com.company;

import java.io.IOException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
//    @Autowired
//    static IMailSender clientA;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Main.class,args);
//        final String chromeDriverPath = "D:/alex/drivers/chromedriver.exe";
//        System.setProperty("webdriver.chrome.driver",chromeDriverPath);
//        ChromeDriver driver = new ChromeDriver();
//    Starter starter = new Starter();
//    starter.start();
    }
}
