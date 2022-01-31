package com.company.test.service;

import com.company.test.model.StockDTO;
import java.util.List;
import org.json.JSONObject;

public interface Parser {
	JSONObject getData();
	List<StockDTO> getStocks();
	List<StockDTO> getStocks(JSONObject jsonObject);
	void serializeStocks(List<StockDTO> stockDTOList);
	JSONObject getDataFromFile();

}
