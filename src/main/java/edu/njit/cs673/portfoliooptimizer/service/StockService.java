package edu.njit.cs673.portfoliooptimizer.service;

import java.util.Map;

import yahoofinance.Stock;

public interface StockService {

	public Map<String, Stock> getStockQuotes(String[] stockSymbols);
}
