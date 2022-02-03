package com.company.test.model;

public interface Session {
	void addStock(StockDTO stock);
	void clearStocks();
	void clearBonds();
	void start();
}
