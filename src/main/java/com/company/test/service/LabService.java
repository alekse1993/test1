package com.company.test.service;

import com.company.test.model.Session;
import com.company.test.model.SessionImpl;
import com.company.test.model.StockDTO;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class LabService {
	Session session;
	Parser parser;

	public LabService(final Parser parser) {
		this.parser = parser;
	}

	public String start(){
		try {
			this.session = SessionImpl.getInstance();
			this.session.start();
			return "Successfully start session and login";
		}
		catch(Exception e){
			return "Error while try to start session and login caused by:\n" + e.getMessage();
		}
	}

	public String addStocks(){
		List<StockDTO> stocks = parser.getStocks();
		try {
			for (StockDTO stock : stocks) {
				session.addStock(stock);
			}
			return "Stocks added successfully!\n" + stocks.stream().map(StockDTO::toString).collect(Collectors.joining());
		}
		catch(Exception e){
			return "Error while add stocks caused by:\n " + e.getMessage();
		}

	}

	public String clearStocks(){
		try{
			session.clearStocks();
			return "Stocks cleared successfully!";
		}
		catch(Exception e){
			return "Error while clear stocks caused by:\n " + e.getMessage();
		}
	}

	public String clearBonds(){
		try{
			session.clearBonds();
			return "Bonds cleared successfully!";
		}
		catch(Exception e){
			return "Error while clear bonds caused by:\n " + e.getMessage();
		}
	}

	public String serialize() {
		try{
			parser.serializeStocks(parser.getStocks(parser.getDataFromFile()));
			return "Serialized successfully";
		}
		catch(Exception e){
			return "Error while serialize caused by:\n" + e.getMessage();
		}
	}

	public List<StockDTO> getStocks() {
		try{
			parser.getStocks();
			return parser.getStocks();
		}
		catch(Exception e){
			return null;
		}
	}
}
