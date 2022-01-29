package com.company.parsing.config;

import java.util.Optional;
import org.hibernate.engine.profile.Fetch;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.devtools.DevTools;

public class NetworkMocker {

    ChromeDriver driver;

    public NetworkMocker(ChromeDriver driver) {
        this.driver = driver;
    }

    public NetworkMocker() {
    }

    public void mocker(){
//        DevTools chromeDevTools = driver.getDevTools();
//        chromeDevTools.createSession();
//        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
//        chromeDevTools.addListener(Network.requestWillBeSent(),
//                entry -> {
//                    System.out.println("Request URI : " + entry.getRequest().getUrl()+"\n"
//                            + " With method : "+entry.getRequest().getMethod() + "\n");
//                    entry.getRequest().getMethod();
//                });
//        driver.get("https://www.google.com");
//        chromeDevTools.send(Network.disable());
    }

}
