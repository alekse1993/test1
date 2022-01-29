package com.company.test;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
                "  \"startDate\": \"2021-12-22\",\n" +
                "  \"endDate\": \"2022-01-29\"\n" +
                "}\n";

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
        headers.add("Cookie","_gcl_au=1.1.1382270770.1643451273; utm_source=google; Segmento_UID=I21QDeXEIGME; _gid=GA1.2.784265446.1643451274; ___dmpkit___=c80cfe4b-82d2-4397-a85b-0d9633be5271; _fbp=fb.1.1643451275384.123380169; top100_id=t1.3122244.1980999422.1643451279419; adtech_uid=514b6eed-76bf-41fe-815f-aac991efa2c3:sberbank.ru; user-id_1.0.5_lr_lruid=pQ8AAI8T9WEMhR5iAU6INAA=; tmr_lvid=145dd74debd750ab2cf598aaf4259b6c; tmr_lvidTS=1643451280344; _ym_uid=1643451281116520423; _ym_d=1643451281; _ym_isad=2; _opt_utmc=investments_corporate_perform_god_20220100063_context_search_brand_rus_desk|15717755643; _gac_UA-21169438-1=1.1643451867.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _gcl_aw=GCL.1643451869.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _sa=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870; last_visit=1643441071287::1643451871287; __zzat2=MDA0dBA=Fz2+aQ==; sbrf.pers_sign=0; DPJSESSIONID=PBC5YS:-1424944279; ERIBDPJSESSIONID=PBC5YS:-1424944279; SWSBTSBOLSESSIONID=PBC5YS:-718662797; dtCookie=-14$MS7LLOKEBSNOA8PJD08S5946CO44JJTA; rxVisitor=164345192205451C7DIG0DCC16CUP5G6DH26J8ON940G3; TS0128b40e=013ade289916ce5c1a47013bd06e73a47fef21dc3a871f1f69455da258ff87f7c2b561326ffcd627a97efdc3ca33087f80f48ae111cee3221387060a091ca31162d5fa87dee2dbcb4554b1a8a81313315799dc46d074069463a83651faeb53839ef722cc3ce7cc446d873dc1455b9fd16e4a0cccfc68b350ba433300f621d1390733572552; sbrf.pers_notice=1; t1_sid_3122244=s1.2143449655.1643488796576.1643488796589.3.4.49; tmr_reqNum=11; utm_medium=organic; utm_content=; utm_campaign=; _dc_gtm_UA-21169438-1=1; _ga=GA1.2.1568790681.1643451274; _gat=1; _sas=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870.1643489922; _ga_2TDLL4T53E=GS1.1.1643488796.3.1.1643489924.0; _gat_ua211694381=1; ERIBSESSIONID=node0fr8743kgdezv1fiem57dc0bxp272915.node0; SBTSBOLSESSIONID=0000IUALhaFGMEJnzyUEp0xAhFA:-1; SBTSBOL_SESSION_TIMESTAMP=1643489907186; UFS-TOKEN=8716ca8e-8962-41a7-b31b-f9ebe10a28ef; UFS-SESSION=Vipwv6DxRyWRmuOSyhnrN0dY1JENuq_DSY6VfTcvAuzLxGZPVAl-AftnePRTu2U2; SWJSESSIONID=4007bb425d299af67256a42d093f6a51; ddp_session_id=ad4fe7dd-ef29-4378-93d6-e6d06b7c5fcf; rxvt=1643491750691|1643489950691; dtPC=-14$89950664_368h1vUXDXQPKUPVQPOJYWPEACMMEATQFCWRSR-0; cfids2=vf5wJbkb3LKXJUjnIsUPteSBNQbwqlSpqg1g4KCF4ZLwkF6B0auKkWdLMxCX8zhNeB+1ZdtqnOztBGzT6fTzexJ5hLDuikxuLbE6U2435QThYL3XBlS2L+03cnmExMqC6Q9jYpjtyaAQWdwPG/JT88p0q1UsCanXdzgQp44=; clsa2=PCXnp22XOjBMN8Gk9hob38pUxuMQkznp6s2QVwz0j9rFUQOP++wansO6FBY/co06rTHxFvr9DCM1v42LXOQWsKqU7LbyJ6ugAXZRc6Jm9wsh+s4GhaLg5zRQkLf1H2G9GsWRVvvV3UAQIZOaHrT9ymk1JeKjArBQPEVWt5oRjE4mxNrj6BTMm3QKAPgAyFZs9UGR3gtmb6M2sfOm62hcg3HIlYEqKXyv8bf5I2GYK2z1VUnb700=");
        headers.add("Content-Type","application/json;charset=UTF-8");

        body.put("agreementId", "S02WDK7");
        body.put("startDate", "2021-12-22");
        body.put("endDate", "2022-01-29");


        HttpEntity<?> httpEntity = new HttpEntity<Object>(body2, headers);


        ResponseEntity<String> response = httpClient.exchange("https://web-node3.online.sberbank.ru/brokerage-info-ib/rest/v1.0/ib/Banking/Product/Brokerage/Agreement/Position/List", HttpMethod.POST, httpEntity, String.class);

        JSONObject jsonObject = new JSONObject(response.getBody());
        return jsonObject;
    }

    public Stock stockFromJson(JSONObject jsonObject){
        DocumentContext jsonContext = JsonPath.parse(jsonObject);
        List<Map<String, Object>> jsonpathCreatorName = jsonContext.read("$.body.position.assets.?");
//        List<String> jsonpathCreatorLocation = jsonContext.read(jsonpathCreatorLocationPath);
        System.out.println(jsonpathCreatorName);
        return new Stock();
    }


}





