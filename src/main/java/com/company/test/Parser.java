package com.company.test;

import com.google.gson.Gson;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.SneakyThrows;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Parser {
//    @Resource
//    @Qualifier ("restTemplate")
    RestTemplate httpClient = new RestTemplate();

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
        headers.add("Cookie","_gcl_au=1.1.1382270770.1643451273; utm_source=google; Segmento_UID=I21QDeXEIGME; _gid=GA1.2.784265446.1643451274; ___dmpkit___=c80cfe4b-82d2-4397-a85b-0d9633be5271; _fbp=fb.1.1643451275384.123380169; top100_id=t1.3122244.1980999422.1643451279419; adtech_uid=514b6eed-76bf-41fe-815f-aac991efa2c3%3Asberbank.ru; user-id_1.0.5_lr_lruid=pQ8AAI8T9WEMhR5iAU6INAA%3D; tmr_lvid=145dd74debd750ab2cf598aaf4259b6c; tmr_lvidTS=1643451280344; _ym_uid=1643451281116520423; _ym_d=1643451281; _gac_UA-21169438-1=1.1643451867.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _gcl_aw=GCL.1643451869.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _sa=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870; last_visit=1643441071287::1643451871287; __zzat2=MDA0dBA=Fz2+aQ==; sbrf.pers_sign=0; sbrf.pers_notice=1; t1_sid_3122244=s1.2143449655.1643488796576.1643488796589.3.4.49; tmr_reqNum=11; utm_medium=organic; utm_content=; utm_campaign=; _ga=GA1.2.1568790681.1643451274; _ym_isad=2; _ga_2TDLL4T53E=GS1.1.1643536236.4.0.1643536241.0; DPJSESSIONID=PBC5YS:-1730254445; ERIBDPJSESSIONID=PBC5YS:-1730254445; SWSBTSBOLSESSIONID=PBC5YS:-519466133; TS0128b40e=013ade2899628ea403e6ee64d5d112f307b864be5da3d3c4926001b8dada6d6e739fba87b59cbc4f1b0e35f5f086edf8196d4e951288be60ca0e2d5f25a5db6de6fbe0227142095659f4d4cd67e5cc09da847563395bfc04994dd5650c6df95c11247e908cba1f51df940067c1ffe229100d338b28f5d9380af2ea87a767f6d44616b15c71; _sas=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870.1643542709; ERIBSESSIONID=node01gzvceq2scycjkcghqcn4otvn4497.node0; SBTSBOLSESSIONID=0000nPLzJUgxqfajfXOgX_eHJjY:-1; SBTSBOL_SESSION_TIMESTAMP=1643542697248; UFS-TOKEN=22321a52-6929-4345-8e15-0bda94dac040; UFS-SESSION=umB0btyIQO6a8BUG0FWvR-ohzJx7IWQf3nWfolYkg0_X1UFoQEBroeAWB7TthTJg; SWJSESSIONID=860b9f40069b9bcf1e01f44f4c6eb2d7; _gat_ua211694381=1; cfids2=dbxbzxLqqL9ATaSHbqAU3yRrUw7VAN9HkZADKdkOyr8XoON3HatN9QVVprpxeMjH4c/03uPBdB10L6cbrw0fa3Ssp0aEv2ufpJnvWVKhyLphbmrmMHHpKV04z46oazo4oJVbDxpnzjrOjk3Q+oPVRjBbY08PDIJ/RT2xEq0=; clsa2=ioxf2SP+3u5+nHu5lqB15KwN5X21wRrICwjuHHHkwvOtaHyQV+bA5s+mKWxBIoXXPo8/2gC4/Lwc4NzfZQaYr/9O3fvQik1zYInRJFJz7O7uTqK+uPLFtdxX7ckHehYSyJDLhxrCcZQf6YnrCElXgbjPDk7pAbk8mbS5xKfWRD5KL4ME9BFD/BQzTK+pKAACs3OrLT92ux4QXGjzO2vOB4KIbc54DdcDKMQYAWuovgiqXTk6K0E=");
        headers.add("Content-Type","application/json;charset=UTF-8");

        body.put("agreementId", "S02WDK7");
        body.put("startDate", "2021-12-22");
        body.put("endDate", "2022-01-29");

        HttpEntity<?> httpEntity = new HttpEntity<Object>(body2, headers);
        ResponseEntity<String> response = httpClient.exchange("https://web-node3.online.sberbank.ru/brokerage-info-ib/rest/v1.0/ib/Banking/Product/Brokerage/Agreement/Position/List", HttpMethod.POST, httpEntity, String.class);
        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject;
    }
    @SneakyThrows
    public List<Stock> getStocks(JSONObject jsonObject){
        DocumentContext jsonContext = JsonPath.parse(jsonObject.toString());
        List<Map<String, Object>> jsonpathCreatorName = jsonContext.read("$.body.position[*].assets[*]");
        List<Stock> stockList = new ArrayList<>();
        for(Map<String, Object> item : jsonpathCreatorName){
            Stock stock = new Stock();
            stock.setMarket(item.get("market").toString());
            stock.setSecCode(item.get("secCode").toString());
            stock.setCurrentCost(new JSONObject(new Gson().toJson(item.get("currentCost"), Map.class)).getString("value"));
            stockList.add(stock);
            System.out.println(stock);
        }
        FileOutputStream fout = new FileOutputStream("C:\\ser\\address.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(stockList);
        return stockList;
    }

    @SneakyThrows
    public List<Stock> getStocks() {
        FileInputStream streamIn = new FileInputStream("C:\\ser\\address.ser");
        ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
        List<Stock> readCase = (List<Stock>) objectinputstream.readObject();
        return readCase;
    }

    public Map<String, GroupStock> groupStocks(List<Stock> stocks) {
        Map<String, GroupStock> groupStockMap = new HashMap<>();

        for(Stock item : stocks){
            if(!groupStockMap.containsKey(item.getSecCode())){
                GroupStock groupStock = new GroupStock();
                groupStock.setSecCode(item.getSecCode());
                groupStock.setCost(Double.valueOf(item.getCurrentCost()));
                List<Stock> stockList = new ArrayList<>();
                stockList.add(item);
                groupStock.setStocks(stockList);
                groupStockMap.put(item.getSecCode(), groupStock);
            }
            else{
                GroupStock groupStock = groupStockMap.get(item.getSecCode());
                groupStock.getStocks().add(item);
                groupStock.setCost(groupStock.getCost() + Double.valueOf(item.getCurrentCost()));
            }
        }
        System.out.println(groupStockMap.get("CHMF").toString());
        return groupStockMap;
    }

    @SneakyThrows
    public void serializeStocks(List<Stock> stockList) {
        FileOutputStream fout = new FileOutputStream("C:\\ser\\address.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fout);
        oos.writeObject(stockList);
    }
}





