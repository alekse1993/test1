package com.company.test;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public class Parser {
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
