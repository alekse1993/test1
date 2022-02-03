package com.company.test.model;

import com.company.test.service.Parser;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

@Slf4j
public class SessionImpl implements Session{
	private static SessionImpl instance;
	private String URL = "https://smart-lab.ru/q/portfolio/LehaNa/47425/";
	private String LOGIN_URL = "https://smart-lab.ru/login/";
	private final String ADD_CLEAR_STOCK_URL ="https://smart-lab.ru/q/portfolio-ajax/";
	String securityLsKey_= null;
	String skey_ = null;
	CookieStore cookieStore_ = new BasicCookieStore();
	boolean initialized = false;

	private SessionImpl(){
	}

	@Override
	public void start(){
		this.initialize();
		this.initialized = true;
	}

	public static SessionImpl getInstance(){
		if(instance == null) {
			return new SessionImpl();
		}
		return instance;
	}

	private void initialize(){
		this.skey_ = this.loginSkey();
		this.securityLsKey_ = this.getSecurityLsKey(this.skey_);
	}

	@Override
	@SneakyThrows
	public void addStock(StockDTO stock) {
		if(this.initialized == true) {
			HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
			StringEntity stringEntity = new StringEntity(getDataString3(getTicker(stock.getName()), securityLsKey_, stock.getCount(), stock.getBuyCost()));
			HttpResponse response = client.execute(getHttpPost(ADD_CLEAR_STOCK_URL, stringEntity));
			String responseBody = EntityUtils.toString(response.getEntity());
			responseBody = responseBody.replace("[", "").replace("]", "");
			log.info("Add: " + responseBody);
		}
	}

	@Override
	@SneakyThrows
	public void clearStocks() {
		if(this.initialized == true) {
			HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
			StringEntity stringEntity = new StringEntity(getDataString4(securityLsKey_));
			HttpResponse response = client.execute(getHttpPost(ADD_CLEAR_STOCK_URL, stringEntity));
			String responseBody = EntityUtils.toString(response.getEntity());
			responseBody = responseBody.replace("[", "").replace("]", "");
			log.info("Add: " + responseBody);
		}
	}

	@Override
	@SneakyThrows
	public void clearBonds() {
		if(this.initialized == true) {
			HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
			StringEntity stringEntity = new StringEntity(getDataString5(securityLsKey_));
			HttpResponse response = client.execute(getHttpPost(ADD_CLEAR_STOCK_URL, stringEntity));
			String responseBody = EntityUtils.toString(response.getEntity());
			responseBody = responseBody.replace("[", "").replace("]", "");
			log.info("Add: " + responseBody);
		}
	}

	@SneakyThrows
	private String getSecurityLsKey(String skey){
		log.info("Cookies2: " + cookieStore_.getCookies().toString());
		BasicClientCookie cookieSKey = new BasicClientCookie("skey",skey);
		cookieStore_.addCookie(cookieSKey);
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
		HttpResponse response = client.execute(getHttpGet(URL));
		String content = getContent(response.getEntity());

		Pattern pattern = Pattern.compile("LIVESTREET_SECURITY_KEY = \\'(\\w*)\\'");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(1);
		}
		return null;
	}

	@SneakyThrows
	private String loginSkey() {
		HttpClientContext context = HttpClientContext.create();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore_);

		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
		HttpResponse response = client.execute(getHttpPost(LOGIN_URL, new StringEntity(getDataString())), context);

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
	private String getTicker(String name) {
		HttpClient client = HttpClientBuilder.create().setDefaultCookieStore(cookieStore_).build();
		log.info("Cookies3: " + cookieStore_.getCookies().toString());
		HttpResponse response = client.execute(getHttpPost("https://smart-lab.ru/q/portfolio-autocomplete-ajax/", new StringEntity(getDataString2(name))));
		String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		responseBody = responseBody.replace("[","").replace("]","");
		JSONObject jsonObject = new JSONObject(responseBody);
		DocumentContext jsonContext = JsonPath.parse(jsonObject.toString());

		String jsonpathCreatorName = jsonContext.read("$.symbol");
		String ticker = jsonpathCreatorName;
		log.info("Ticker: " + ticker);
		return ticker;
	}

	@SneakyThrows
	private HttpPost getHttpPost(String url, StringEntity stringEntity) {
		HttpPost httpPost = new HttpPost(url);

		httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:96.0) Gecko/20100101 Firefox/96.0");
		httpPost.addHeader("Accept", "*/*");
		httpPost.addHeader("Accept-Language", "ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3");
		//        httpPost.addHeader("Accept-Encoding", "gzip, deflate, br");
		httpPost.addHeader("Referer", URL);
		httpPost.addHeader("Origin", "https://smart-lab.ru");
		httpPost.addHeader("Connection", "keep-alive");
		httpPost.addHeader("Upgrade-Insecure-Requests", "1");
		httpPost.addHeader("Sec-Fetch-Dest", "document");
		httpPost.addHeader("Sec-Fetch-Mode", "navigate");
		httpPost.addHeader("Sec-Fetch-Site", "same-origin");
		httpPost.addHeader("Sec-Fetch-User", "?1");
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		httpPost.addHeader("Host", "smart-lab.ru");
		httpPost.addHeader("TE", "trailers");
		httpPost.addHeader("X-Requested-With", "XMLHttpRequest");
		httpPost.setEntity(stringEntity);

		return httpPost;
	}

	@SneakyThrows
	private HttpGet getHttpGet(String url) {
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

	//#####################
	private String getDataString() throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<>();
		params.put("login","lehana2000@mail.ru");
		params.put("password", "Pa$$w0rd");
		params.put("remember", "on");
		params.put("submit_login","1");
		return DataUtils.urlEncoded(params);
	}

	private String getDataString2(String name) throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<>();
		params.put("json","1");
		params.put("value", name);
		return DataUtils.urlEncoded(params);
	}

	private String getDataString3(String ticker, String securituLsKey, String qty, String price) throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<>();
		params.put("action","buy");
		params.put("quantity", qty);
		params.put("price", price);
		params.put("portfolio_id", "47425");
		params.put("security_ls_key", securituLsKey);
		params.put("symbol", ticker);
		params.put("sec_type", "SHARES");
		return DataUtils.urlEncoded(params);
	}

	private String getDataString4(String securituLsKey) throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<>();
		params.put("action","clear_list");
		params.put("id", "47425");
		params.put("type", "shares");
		params.put("security_ls_key", securituLsKey);
		return DataUtils.urlEncoded(params);
	}

	private String getDataString5(String securituLsKey) throws UnsupportedEncodingException {
		HashMap<String, String> params = new HashMap<>();
		params.put("action","clear_list");
		params.put("id", "47425");
		params.put("type", "bonds");
		params.put("security_ls_key", securituLsKey);
		return DataUtils.urlEncoded(params);
	}
}
