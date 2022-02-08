package com.company.test.service.impl;

import com.company.test.model.DataUtils;
import com.company.test.model.StockDTO;
import com.company.test.service.Parser;
import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ParserImpl implements Parser {
//    @Resource
//    @Qualifier ("restTemplate")
    RestTemplate httpClient;
    CookieStore cookieStore_;

    public ParserImpl() {
        this.cookieStore_ =  new BasicCookieStore();
        // JSESSIONID
    }

    @Override
    public JSONObject getData(){
        Map<String,String> body = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        String body2 = "{\n" +
                "  \"agreementId\": \"S02WDK7\",\n" +
                "  \"startDate\": \"2021-12-23\",\n" +
                "  \"endDate\": \"2022-01-30\"\n" +
                "}";

        headers.add("Connection","keep-alive");
        headers.add("sec-ch-ua", "\"Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"");
        headers.add("Accept","application/json, text/plain, */*");
        headers.add("X-Requested-With","XMLHttpRequest");
        headers.add("sec-ch-ua-mobile",  "?0");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");
        headers.add("sec-ch-ua-platform", "Windows");
        headers.add("Origin","https://web3.online.sberbank.ru");
        headers.add("Sec-Fetch-Site","same-site");
        headers.add("Sec-Fetch-Mode","cors");
        headers.add("Sec-Fetch-Dest","empty");
        headers.add("Referer","https://web3.online.sberbank.ru/");
        headers.add("Accept-Language","ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
        headers.add("Cookie","_gcl_au=1.1.1382270770.1643451273; utm_source=google; Segmento_UID=I21QDeXEIGME; _gid=GA1.2.784265446.1643451274; ___dmpkit___=c80cfe4b-82d2-4397-a85b-0d9633be5271; _fbp=fb.1.1643451275384.123380169; top100_id=t1.3122244.1980999422.1643451279419; adtech_uid=514b6eed-76bf-41fe-815f-aac991efa2c3:sberbank.ru; user-id_1.0.5_lr_lruid=pQ8AAI8T9WEMhR5iAU6INAA=; tmr_lvid=145dd74debd750ab2cf598aaf4259b6c; tmr_lvidTS=1643451280344; _ym_uid=1643451281116520423; _ym_d=1643451281; _gac_UA-21169438-1=1.1643451867.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _gcl_aw=GCL.1643451869.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _sa=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870; last_visit=1643441071287::1643451871287; __zzat2=MDA0dBA=Fz2+aQ==; sbrf.pers_sign=0; sbrf.pers_notice=1; t1_sid_3122244=s1.2143449655.1643488796576.1643488796589.3.4.49; tmr_reqNum=11; utm_medium=organic; utm_content=; utm_campaign=; _ga=GA1.2.1568790681.1643451274; _ym_isad=2; _ga_2TDLL4T53E=GS1.1.1643536236.4.0.1643536241.0; DPJSESSIONID=PBC5YS:-1730254445; ERIBDPJSESSIONID=PBC5YS:-1730254445; SWSBTSBOLSESSIONID=PBC5YS:-519466133; dtCookie=-4$DCIERQE80P5ORM33TGM8OTUM55HRD81R; rxVisitor=1643536267826S89LBIPNCKJKQFBD7E9N7RHCUSO6JDFM; TS0128b40e=013ade2899628ea403e6ee64d5d112f307b864be5da3d3c4926001b8dada6d6e739fba87b59cbc4f1b0e35f5f086edf8196d4e951288be60ca0e2d5f25a5db6de6fbe0227142095659f4d4cd67e5cc09da847563395bfc04994dd5650c6df95c11247e908cba1f51df940067c1ffe229100d338b28f5d9380af2ea87a767f6d44616b15c71; _sas=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870.1643542709; ERIBSESSIONID=node0p8d200ol9sqm1duk5ca8pzc5j5301.node0; SBTSBOLSESSIONID=0000APgWpGDdIEcLUbhY-DufKoj:-1; SBTSBOL_SESSION_TIMESTAMP=1643546949958; UFS-TOKEN=302d9ca7-b536-4fb3-8633-748b5618ac7d; UFS-SESSION=Jhair-tgSk6zCAraJQOwHcEQVKdZjritK6FNHjfPQnpiFNpgMsPmAYPggjD-V4ui; SWJSESSIONID=a4c069929870a971c7620ab0f3777d8c; ddp_session_id=620d4ecb-917f-45e2-a6b3-6398bbed5f02; rxvt=1643550186039|1643546985561; dtPC=-4$147517905_544h1p-4$148386016_900h1vNNVOZXLBIXASXUFHZKROOESQACRWILEY-0; cfids2=HTZV5aRAlo+aI7Wq4s7nedrPsq/cDZ51hpFRxCuvDM/VPJ+o5L6bo2yb3MaplZOkh1pliRfleAU//h1p31XYe2iMofIKHs8tSODpxABNyNLYW/xPk3O5HL9YeJWwcers1jvLiOGRGOC1cO9YS1zyvYXnPLtCk0vn3v2UFzw=; clsa2=P4KX7ekBr43Fqauv6QzWMUNbVNGnbd/pcv/gt0b2hot3pjw0TynmSeXnRbJrwwk052xVuQ5jG/len5JJAOOWVvpyoML6kuuRe+N079cWFASi5XX0xoObcF598gkUneb1KMiabGS0MW5FVw7SfDLTUWorl6KY7BwEzrR2AxiZQRk4OzmZU5JXhDJZkQQbVe79iLtvl4Lv4/WSeDJtL4QAfVBQdSYdgm4/gTck1SCR25Jx8HVYDi4=");
        headers.add("Content-Type","application/json;charset=UTF-8");

        body.put("agreementId", "S02WDK7");
        body.put("startDate", "2021-12-22");
        body.put("endDate", "2022-01-29");

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body2, headers);
        ResponseEntity<String> response = httpClient.exchange("https://web-node3.online.sberbank.ru/brokerage-info-ib/rest/v1.0/ib/Banking/Product/Brokerage/Agreement/Position/List", HttpMethod.POST, httpEntity, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject;
    }

    @Override
    @SneakyThrows
    public List<StockDTO> getStocks(JSONObject jsonObject){
        DocumentContext jsonContext = JsonPath.parse(jsonObject.toString());
        List<Map<String, Object>> jsonpathCreatorName = jsonContext.read("$.body.position[-1:].assets[*]");
        List<StockDTO> stockDTOList = new ArrayList<>();
        for(Map<String, Object> item : jsonpathCreatorName){
            StockDTO stockDTO = new StockDTO();
            try{
                stockDTO.setMarket(item.get("market").toString());
                stockDTO.setSecCode(item.get("secCode").toString());
                stockDTO.setCurrentCost(new JSONObject(new Gson().toJson(item.get("currentCost"), Map.class)).getString("value"));
                stockDTO.setName(new JSONObject(new Gson().toJson(item.get("portfolio"), Map.class)).get("securities").toString());

                stockDTO.setCount(new JSONObject(new Gson().toJson(item.get("portfolio"), Map.class)).get("planBalance").toString());
                JSONObject jsonObject1 = (JSONObject) new JSONObject(new Gson().toJson(item.get("portfolio"), Map.class)).get("balanceCost");
                stockDTO.setBuyCost(jsonObject1.getString("value"));
                stockDTOList.add(stockDTO);
                System.out.println(stockDTO);
            }
            catch(Exception e){

            }
        }
        FileOutputStream fout = new FileOutputStream("C:\\ser\\address.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(stockDTOList);
        return stockDTOList;
    }

    @Override
    @SneakyThrows
    public List<StockDTO> getStocks() {
        FileInputStream streamIn = new FileInputStream("C:\\ser\\address.ser");
        ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
        List<StockDTO> stockDTOList = (List<StockDTO>) objectinputstream.readObject();
        getSberLoginData();
        return stockDTOList;
    }

    @Override
    @SneakyThrows
    public void serializeStocks(List<StockDTO> stockDTOList) {
        FileOutputStream fout = new FileOutputStream("C:\\ser\\address.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(stockDTOList);
    }

    @Override
    @SneakyThrows
    public JSONObject getDataFromFile() {
        File file = getFileFromResource("data.json");
        byte[] encoded = Files.readAllBytes(Paths.get(file.getPath()));
        String str = new String(encoded, StandardCharsets.UTF_8);
        JSONObject jsonObject = new JSONObject(str);
        return jsonObject;
    }

    private File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

    private String getSberLoginData() {
        HashMap<String, String> params = new HashMap<>();
        params.put("deviceprint","version=1.7.3&pm_br=Firefox&pm_brmjv=96&iframed=0&intip=&pm_expt=&pm_fpacn=Mozilla&pm_fpan=Netscape&pm_fpasw=&pm_fpco=1&pm_fpjv=0&pm_fpln=lang=ru-RU|syslang=|userlang=&pm_fpol=true&pm_fposp=&pm_fpsaw=1536&pm_fpsbd=&pm_fpsc=24|1536|864|824&pm_fpsdx=&pm_fpsdy=&pm_fpslx=&pm_fpsly=&pm_fpspd=24&pm_fpsui=&pm_fpsw=&pm_fptz=3&pm_fpua=mozilla/5.0 (windows nt 10.0; win64; x64; rv:96.0) gecko/20100101 firefox/96.0|5.0 (Windows)|Win32&pm_fpup=&pm_inpt=&pm_os=Windows&adsblock=0=false|1=false|2=false|3=false|4=false&audio=baseLatency=0|outputLatency=0|sampleRate=48000|state=suspended|maxChannelCount=2|numberOfInputs=1|numberOfOutputs=1|channelCount=2|channelCountMode=max|channelInterpretation=speakers|fftSize=2048|frequencyBinCount=1024|minDecibels=-100|maxDecibels=-30|smoothingTimeConstant=0.8&pm_fpsfse=true&webgl=ver=webgl2|vendor=Google Inc. (Intel)|render=ANGLE (Intel(R) HD Graphics 400 Direct3D11 vs_5_0 ps_5_0, D3D11-27.20.100.8681)");
        params.put("jsEvents", "");
        params.put("domElements", "");
        params.put("operation", "button.begin");
        params.put("login","");
        params.put("pageInputType", "INDEX");
        params.put("password", "Pa$$w0rd");
        params.put("loginStored", "true");
        return DataUtils.urlEncoded(params);
    }

    @SneakyThrows
    private HttpPost getHttpPost(String url, StringEntity stringEntity) {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0");
        httpPost.addHeader("Accept", "application/json, text/plain, */*");
        httpPost.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        //        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
        httpPost.addHeader("Referer", "https://online.sberbank.ru/CSAFront/index.do");
        httpPost.addHeader("Origin", "https://online.sberbank.ru");
        httpPost.addHeader("Connection", "keep-alive");
        httpPost.addHeader("Sec-Fetch-Dest", "empty");
        httpPost.addHeader("Sec-Fetch-Mode", "cors");
        httpPost.addHeader("Sec-Fetch-Site", "same-origin");
        httpPost.addHeader("Sec-Fetch-User", "?1");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//        httpPost.addHeader("Page-Id", "#/");
//        httpPost.addHeader("Content-Length", "1371");
//        httpPost.addHeader("Host", "online.sberbank.ru");
        httpPost.addHeader("X-TS-AJAX-Request", "true");
        httpPost.setEntity(stringEntity);

        return httpPost;
    }

    @SneakyThrows
    public void loginSber() {
            HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
            StringEntity stringEntity = new StringEntity(getSberLoginData());
            HttpPost httpPost = getHttpPost("https://online.sberbank.ru/CSAFront/authMainJson.do", stringEntity);
            HttpResponse response = client.execute(httpPost);
            String responseBody = EntityUtils.toString(response.getEntity());
            responseBody = responseBody.replace("[", "").replace("]", "");
            log.info(responseBody);

    }
}

//    ESAWEBJSESSIONID




