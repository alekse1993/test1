package com.company.parsing.config;

import javax.annotation.PostConstruct;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

//import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumConfiguration {

    @Value("${chrome.driver.path}")
    final String chromeDriverPath = "D:/alex/drivers/chromedriver.exe";
//    final String phantomDriverPath = "D:/alex/drivers/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    ChromeOptions options;
//    DesiredCapabilities capabilities;
//    DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, fileDriverPath);

    @PostConstruct
    void PostConstruct(){
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);

        options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");

//        System.setProperty("phantomjs.binary.path", phantomDriverPath);
//        capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomDriverPath);

    }

    @Bean
    public ChromeDriver driver(){
        return new ChromeDriver(options);
    }

//    @Bean
//    public PhantomJSDriver driver(){
//        return new PhantomJSDriver(capabilities);
//    }
}
