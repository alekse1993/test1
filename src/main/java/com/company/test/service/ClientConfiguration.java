package com.company.test.service;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ClientConfiguration {
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private String url = "https://smart-lab.ru/q/portfolio/LehaNa/";
    private String body = "{\\n\" +\n" +
            "                \"  \\\"agreementId\\\": \\\"S02WDK7\\\",\\n\" +\n" +
            "                \"  \\\"startDate\\\": \\\"2021-12-23\\\",\\n\" +\n" +
            "                \"  \\\"endDate\\\": \\\"2022-01-30\\\"\\n\" +\n" +
            "                \"}";

    public CookieStore getCookieStore() {
        HttpPost httppost = new HttpPost();
        CookieStore cookieStore = new BasicCookieStore();
        BasicClientCookie cookie = new BasicClientCookie("PHPSESSID","c7e21a03581bd977f2f722e228038944");
        // JSESSIONID
        cookie.setPath("/");
        cookieStore.addCookie(cookie);
        return cookieStore;
    }
    @SneakyThrows
    public String getClient() {
        CloseableHttpResponse response = httpClient.execute(getHttpPost());
        return response.toString() + "\n" + getContent(response.getEntity());
//        return httpClient.execute(getHttpPost()).toString();
    }

    @SneakyThrows
    public HttpPost getHttpPost() {
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader("Connection","keep-alive");
        httpPost.addHeader("sec-ch-ua", "\"Not A;Brand\";v=\"99\", \"Chromium\";v=\"96\", \"Google Chrome\";v=\"96\"");
        httpPost.addHeader("Accept","application/json, text/plain, */*");
        httpPost.addHeader("X-Requested-With","XMLHttpRequest");
        httpPost.addHeader("sec-ch-ua-mobile",  "?0");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");
        httpPost.addHeader("sec-ch-ua-platform", "Windows");
        httpPost.addHeader("Origin","https://web3.online.sberbank.ru");
        httpPost.addHeader("Sec-Fetch-Site","same-site");
        httpPost.addHeader("Sec-Fetch-Mode","cors");
        httpPost.addHeader("Sec-Fetch-Dest","empty");
        httpPost.addHeader("Referer","https://web3.online.sberbank.ru/");
        httpPost.addHeader("Accept-Language","ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7");
//        headers.add("Cookie","_gcl_au=1.1.1382270770.1643451273; utm_source=google; Segmento_UID=I21QDeXEIGME; _gid=GA1.2.784265446.1643451274; ___dmpkit___=c80cfe4b-82d2-4397-a85b-0d9633be5271; _fbp=fb.1.1643451275384.123380169; top100_id=t1.3122244.1980999422.1643451279419; adtech_uid=514b6eed-76bf-41fe-815f-aac991efa2c3:sberbank.ru; user-id_1.0.5_lr_lruid=pQ8AAI8T9WEMhR5iAU6INAA=; tmr_lvid=145dd74debd750ab2cf598aaf4259b6c; tmr_lvidTS=1643451280344; _ym_uid=1643451281116520423; _ym_d=1643451281; _gac_UA-21169438-1=1.1643451867.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _gcl_aw=GCL.1643451869.EAIaIQobChMI1ryfn93W9QIVSfgYCh2MsAp5EAAYASAAEgIxhvD_BwE; _sa=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870; last_visit=1643441071287::1643451871287; __zzat2=MDA0dBA=Fz2+aQ==; sbrf.pers_sign=0; sbrf.pers_notice=1; t1_sid_3122244=s1.2143449655.1643488796576.1643488796589.3.4.49; tmr_reqNum=11; utm_medium=organic; utm_content=; utm_campaign=; _ga=GA1.2.1568790681.1643451274; _ym_isad=2; _ga_2TDLL4T53E=GS1.1.1643536236.4.0.1643536241.0; DPJSESSIONID=PBC5YS:-1730254445; ERIBDPJSESSIONID=PBC5YS:-1730254445; SWSBTSBOLSESSIONID=PBC5YS:-519466133; dtCookie=-4$DCIERQE80P5ORM33TGM8OTUM55HRD81R; rxVisitor=1643536267826S89LBIPNCKJKQFBD7E9N7RHCUSO6JDFM; TS0128b40e=013ade2899628ea403e6ee64d5d112f307b864be5da3d3c4926001b8dada6d6e739fba87b59cbc4f1b0e35f5f086edf8196d4e951288be60ca0e2d5f25a5db6de6fbe0227142095659f4d4cd67e5cc09da847563395bfc04994dd5650c6df95c11247e908cba1f51df940067c1ffe229100d338b28f5d9380af2ea87a767f6d44616b15c71; _sas=SA1.f12be27c-335d-44ce-bfd3-118af6183741.1643451870.1643542709; ERIBSESSIONID=node0p8d200ol9sqm1duk5ca8pzc5j5301.node0; SBTSBOLSESSIONID=0000APgWpGDdIEcLUbhY-DufKoj:-1; SBTSBOL_SESSION_TIMESTAMP=1643546949958; UFS-TOKEN=302d9ca7-b536-4fb3-8633-748b5618ac7d; UFS-SESSION=Jhair-tgSk6zCAraJQOwHcEQVKdZjritK6FNHjfPQnpiFNpgMsPmAYPggjD-V4ui; SWJSESSIONID=a4c069929870a971c7620ab0f3777d8c; ddp_session_id=620d4ecb-917f-45e2-a6b3-6398bbed5f02; rxvt=1643550186039|1643546985561; dtPC=-4$147517905_544h1p-4$148386016_900h1vNNVOZXLBIXASXUFHZKROOESQACRWILEY-0; cfids2=HTZV5aRAlo+aI7Wq4s7nedrPsq/cDZ51hpFRxCuvDM/VPJ+o5L6bo2yb3MaplZOkh1pliRfleAU//h1p31XYe2iMofIKHs8tSODpxABNyNLYW/xPk3O5HL9YeJWwcers1jvLiOGRGOC1cO9YS1zyvYXnPLtCk0vn3v2UFzw=; clsa2=P4KX7ekBr43Fqauv6QzWMUNbVNGnbd/pcv/gt0b2hot3pjw0TynmSeXnRbJrwwk052xVuQ5jG/len5JJAOOWVvpyoML6kuuRe+N079cWFASi5XX0xoObcF598gkUneb1KMiabGS0MW5FVw7SfDLTUWorl6KY7BwEzrR2AxiZQRk4OzmZU5JXhDJZkQQbVe79iLtvl4Lv4/WSeDJtL4QAfVBQdSYdgm4/gTck1SCR25Jx8HVYDi4=");
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");

        StringEntity stringEntity = new StringEntity(getDataString());
        httpPost.setEntity(stringEntity);

        httpClient.setCookieStore(getCookieStore());
//        response = client.execute(httppost);
        return httpPost;
    }

    private String getDataString() throws UnsupportedEncodingException {
        HashMap<String, String> params = new HashMap<>();
        params.put("login","lehana2000@mail.ru");
        params.put("password", "Pa$$w0rd");
        params.put("remember", "on");
        params.put("submit_login","1");
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    private String getContent(HttpEntity entity){
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(entity.getContent()), 65728);
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
        catch (Exception e) { e.printStackTrace(); }


        return sb.toString();
    }
}
