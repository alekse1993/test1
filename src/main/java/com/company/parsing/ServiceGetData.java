package com.company.parsing;

import java.util.Collections;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.apache.http.client.HttpClient;

public class ServiceGetData {
    RestTemplate httpClient = this.restTemplate();
    private static final String WILDBERRIES_URL = "https://www.wildberries.ru";
    RestTemplate restTemplate() {
        HttpClient httpClient = HttpClients.custom().build();
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(httpClient);
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.setInterceptors(Collections.singletonList(new RestInterceptor()));
        return restTemplate;
    }
    public void getJson(Integer id){

        HttpHeaders headers = new HttpHeaders();
        headers.add("x-spa-version","8.0.37");
        headers.add("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:93.0) Gecko/20100101 Firefox/93.0");
        headers.add("Accept","*/*");
        headers.add("Accept-Language","ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
        headers.add("Referer","https://www.wildberries.ru/catalog/"+id+"/detail.aspx?targetUrl=MI");
        headers.add("x-requested-with","XMLHttpRequest");
        headers.add("Connection","keep-alive");
        headers.add("Sec-Fetch-Des","empty");
        headers.add("Sec-Fetch-Mode","cors");
        headers.add("Sec-Fetch-Site","same-origin");
        headers.add("TE","trailers");




        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = httpClient.exchange(WILDBERRIES_URL + "/" + id + "/product/data?targetUrl=AB", HttpMethod.GET, httpEntity, String.class);

    }
}
