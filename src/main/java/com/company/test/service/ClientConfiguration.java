package com.company.test.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.GZIPInputStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class ClientConfiguration {
    private DefaultHttpClient httpClient = new DefaultHttpClient();
    private String url = "https://smart-lab.ru/q/portfolio/LehaNa/47425/";
    private String loginUrl = "https://smart-lab.ru/login/";
    CookieStore cookieStore_ = new BasicCookieStore();

    @SneakyThrows
    public String loginSkey() {
        HttpClientContext context = HttpClientContext.create();
        context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore_);

        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
        HttpResponse response = client.execute(getHttpPost(loginUrl), context);

        List<Cookie> customCookie = context.getCookieStore().getCookies();
        Optional<Cookie> optional1 = customCookie.stream().filter(e->e.getName().equals("skey")).findFirst();
        Optional<Cookie> optional2 = customCookie.stream().filter(e->e.getName().equals("visitor_id")).findFirst();
        String skey = optional1.isPresent()?optional1.get().getValue():null;
        String visitorId = optional2.isPresent()?optional2.get().getValue():null;

        log.info("skey: " + skey);
        log.info("visitor_id: " + visitorId);
        log.info("Cookies: " + customCookie.toString());
        log.info("Response:" + response.toString());

        return skey;
    }

    @SneakyThrows
    public String getClient() {
        log.info("Cookies2: " + cookieStore_.getCookies().toString());
        BasicClientCookie cookieSKey = new BasicClientCookie("skey",loginSkey());
        cookieStore_.addCookie(cookieSKey);
        HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
        HttpResponse response = client.execute(getHttpGet(url));

        return response.toString() + "\n" + "\n" + getContent(response.getEntity());
    }

    @SneakyThrows
    public HttpPost getHttpPost(String url) {
        HttpPost httpPost = new HttpPost(url);

//        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0");
        httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        httpPost.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
//        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
//        httpPost.addHeader("Referer", "https://smart-lab.ru/login/");
//        httpPost.addHeader("Origin", "https://smart-lab.ru");
//        httpPost.addHeader("Connection", "keep-alive");
//        httpPost.addHeader("Upgrade-Insecure-Requests", "1");
//        httpPost.addHeader("Sec-Fetch-Dest", "document");
//        httpPost.addHeader("Sec-Fetch-Mode", "navigate");
//        httpPost.addHeader("Sec-Fetch-Site", "same-origin");
//        httpPost.addHeader("Sec-Fetch-User", "?1");
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");

        StringEntity stringEntity = new StringEntity(getDataString());
        httpPost.setEntity(stringEntity);

        return httpPost;
    }

    @SneakyThrows
    public HttpGet getHttpGet(String url) {
        HttpGet httpGet = new HttpGet(url);

//        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0");
        httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8");
        httpGet.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
//        httpGet.addHeader("Accept-Encoding", "gzip, deflate, br");
//        httpGet.addHeader("Referer", "https://smart-lab.ru/login/");
//        httpGet.addHeader("Origin", "https://smart-lab.ru/q/portfolio/LehaNa/");
//        httpGet.addHeader("Connection", "keep-alive");
//        httpGet.addHeader("Upgrade-Insecure-Requests", "1");
//        httpGet.addHeader("Sec-Fetch-Dest", "document");
//        httpGet.addHeader("Sec-Fetch-Mode", "navigate");
//        httpGet.addHeader("Sec-Fetch-Site", "same-origin");
//        httpGet.addHeader("Sec-Fetch-User", "?1");
        httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded");

        return httpGet;
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
        log.info(result.toString());
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

    public String decompressGzip(HttpEntity entity) throws IOException {
        InputStream contentStream = new ByteArrayInputStream(entity.getContent().readAllBytes());
        StringBuilder sb = new StringBuilder();
        try{
            GZIPInputStream gis = new GZIPInputStream(contentStream);
            byte[] buffer = new byte[1024];
            int len;
            String str = "";
            while ((len = gis.read(buffer)) > 0) {
                str = new String(buffer, StandardCharsets.UTF_8);
                sb.append(str);
            }

        }catch(Exception e){

        }
        return sb.toString();
    }
}
