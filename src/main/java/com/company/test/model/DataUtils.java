package com.company.test.model;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;

public class DataUtils {
	@SneakyThrows
	public static String urlEncoded(Map<String,String> map){
		HashMap<String, String> params = (HashMap<String, String>) map;
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
}
