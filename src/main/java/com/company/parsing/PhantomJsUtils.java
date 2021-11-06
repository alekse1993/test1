package com.company.parsing;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;

public class PhantomJsUtils {
//    private static String filePath = "src/main/resources/";
//    private static String scriptPath = "D:\\alex\\test1\\src\\main\\resources\\parserjs.js";
//    private static String fileDriverPath = "D:\\alex\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe ";
//
//
//    public static Document renderPage(String filePath) {
////                System.setProperty("phantomjs.binary.path", fileDriverPath+" "+scriptPath); // path to bin file. NOTE: platform dependent
////                WebDriver driver = new PhantomJSDriver();
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, fileDriverPath);
//        capabilities.setJavascriptEnabled(true);
//        capabilities.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[] {
//                "--ssl-protocol=any"
//        });
//
////        capabilities.setCapability("localToRemoteUrlAccessEnabled", true);
//        WebDriver driver = new PhantomJSDriver(capabilities);
//        try {
//
//            driver.get(filePath);
//            PhantomJsUtils.savePage(Jsoup.parse(driver.getPageSource()));
//            return Jsoup.parse(driver.getPageSource());
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            driver.quit();
//        }
//    }
//
//    public static void savePage(Document doc) throws IOException {
//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        String tmpFileName = filePath + timestamp.toString().replace(':','_').replace(' ','_')+".html";
//        File file = new File(tmpFileName);
//        file.createNewFile();
//        Files.write( Paths.get(tmpFileName), (doc.toString()).getBytes());
//    }
}
