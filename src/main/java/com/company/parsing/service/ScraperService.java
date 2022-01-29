package com.company.parsing.service;

import org.apache.commons.lang3.StringUtils;
import com.company.parsing.config.ShuffleList;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v95.network.Network;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScraperService {
    private static final String URL = "https://www.wildberries.ru/catalog/";
    @Autowired
    private ChromeDriver driver;

    @Autowired
    private ShuffleList shuffle;
    //    @Value("${path.to.save.html")
    //    String filePath;

    @PostConstruct
    void postConstruct() throws IOException {
        try {
            scrape(shuffle);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
    Object getJson(JSONObject jsonObject, String field) {
        try {
            Object result = jsonObject.get(field);
            if (result instanceof String) {
                return StringUtils.isNotBlank((String) result) ? result : null;
            } else {
                return result;
            }
        } catch (JSONException e) {
            System.out.printf("Field %s is null because %s", field, e.getMessage());
            return null;
        }
    }
    private JSONObject getJsonObjectByRegex(Matcher matcher) {
        JSONObject jsonObject = new JSONObject();
        if (matcher.find()) {
            String source = matcher.group(2);
            //            System.out.println(matcher.group(2));
            try {
                JSONObject jsonObject2 = new JSONObject(source);
                jsonObject = jsonObject2;
//                System.out.println(jsonObject);
            } catch (JSONException err) {
                System.out.println(err.toString());
            }

        }
        return jsonObject;
    }
public void scrape2(){

    DevTools chromeDevTools = driver.getDevTools();
    chromeDevTools = driver.getDevTools();
    chromeDevTools.createSession();

    chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
    chromeDevTools.addListener(Network.requestWillBeSent(), entry -> {

        System.out.println("Request URI : " + entry.getRequest().getUrl() + "\n" + " With method : " + entry.getRequest().getMethod() + "\n");
        entry.getRequest().getMethod();
    });
    driver.get(URL + 18640759 + "/detail.aspx");
    chromeDevTools.send(Network.disable());
    driver.quit();
}
    public void scrape(ShuffleList shuffle) throws IOException {
        final long startTimeT = System.currentTimeMillis();
        ArrayList<Integer> ids = shuffle.getShuffledList();
        for(Integer id : ids){
            final long startTime = System.currentTimeMillis();
            //        WebDriverManager.chromedriver().setup();
            //        WebDriver driver = new ChromeDriver();
            driver.navigate().to(URL+id+"/detail.aspx");
            //        driver.get(URL+id+"/detail.aspx");

            //        driver.manage().window().maximize();
            //        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
            String source = driver.getPageSource();
            //        System.out.println(teststr);
            Pattern pattern = Pattern.compile("(ssrModel:{1})( .*),");
            Pattern pattern2 = Pattern.compile("(modules:{1})( .*),");
            Matcher matcher = pattern.matcher(source);
            Matcher matcher2 = pattern2.matcher(source);
            JSONObject objJson = this.getJsonObjectByRegex(matcher);
            JSONObject objJson2 = this.getJsonObjectByRegex(matcher2);
            try{
                JSONObject obj = (JSONObject) objJson.get("product");
                String brandName = (String) getJson(obj,"brandName");
                String description = (String) getJson(obj,"description");
                Integer brandId = (Integer) getJson(obj,"brandId");
                String goodsName = (String) getJson(obj,"goodsName");
                final long endTime = System.currentTimeMillis();
                System.out.println("#####"+"\n"+brandName+"\n"+description+"\n"+brandId+"\n"+goodsName+"\n"+"#####"+"\n");
                System.out.println("time: " + (endTime - startTime)*0.001);
            }
            catch(Exception e){
                System.out.println("product Not found\n"+e.getMessage());
            }

        }
        final long endTimeT = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTimeT - startTimeT)*0.001);

//                if (matcher.find())
//                            {
//        System.out.println(matcher.group(1));
//                            }



//        this.savePage(Jsoup.parse(source));
//        WebElement element = driver.findElementById("productNmId");
//
//        String text = element.getText();
//        System.out.println(text);
        driver.quit();
//        System.out.println(id);
    }

    public void savePage(Document doc) throws IOException {
        String filePath = "src/main/resources/";
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String tmpFileName = filePath + timestamp.toString().replace(':','_').replace(' ','_')+".html";
        File file = new File(tmpFileName);
        file.createNewFile();
        Files.write( Paths.get(tmpFileName), (doc.toString()).getBytes());
    }
}
