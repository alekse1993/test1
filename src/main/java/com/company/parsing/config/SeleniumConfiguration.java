package com.company.parsing.config;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;


public class SeleniumConfiguration {

    @Value("${chrome.driver.path}")
    final String chromeDriverPath = "D:/alex/drivers/chromedriver.exe";
//    final String phantomDriverPath = "D:/alex/drivers/phantomjs-2.1.1-windows/bin/phantomjs.exe";
    ChromeOptions options;
//    DesiredCapabilities capabilities;
//    DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, fileDriverPath);


    void PostConstruct(){
        System.setProperty("webdriver.chrome.driver",chromeDriverPath);

        options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors");

//        System.setProperty("phantomjs.binary.path", phantomDriverPath);
//        capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, phantomDriverPath);

    }


    public ChromeDriver driver(){
        return new ChromeDriver(options);
    }

//    @Bean
//    public PhantomJSDriver driver(){
//        return new PhantomJSDriver(capabilities);
//    }
}
