package edu.njit.cs673.portfoliooptimizer.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class StockServiceImpl implements StockService {

	@Override
	public Map<String, Stock> getStockQuotes(String[] stockSymbols) {
		return YahooFinance.get(stockSymbols);
	}

}
