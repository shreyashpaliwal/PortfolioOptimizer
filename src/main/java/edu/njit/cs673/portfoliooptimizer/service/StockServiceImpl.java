package edu.njit.cs673.portfoliooptimizer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.c673.portfoliooptimizer.model.StockPerformance;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Service
public class StockServiceImpl implements StockService {

	private static final Logger log = Logger.getLogger(StockServiceImpl.class);

	@Override
	public Map<String, Stock> getStockQuotes(String[] stockSymbols) {
		try {
			return YahooFinance.get(stockSymbols);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<StockPerformance> getStockPerformance(List<PortfolioStock> portfolioStocks) throws IOException {

		Map<String, BigDecimal> stockBuyPriceMap = new HashMap<String, BigDecimal>();

		for (PortfolioStock stock : portfolioStocks) {
			stockBuyPriceMap.put(stock.getStockSymbol(), stock.getPurchasePrice());
		}

		Map<String, Stock> stockMap = null;

		List<StockPerformance> stockPerformanceList = new ArrayList<StockPerformance>();

		stockMap = YahooFinance.get(stockBuyPriceMap.keySet().toArray(new String[stockBuyPriceMap.keySet().size()]));

		for (Map.Entry<String, Stock> entry : stockMap.entrySet()) {
			StockPerformance stockPerformance = new StockPerformance();

			for (PortfolioStock stock : portfolioStocks) {

				if (stock.getStockSymbol().equals(entry.getKey())) {
					stockPerformance.setStock(stock);
					break;
				}
			}

			if (entry.getValue().getQuote() != null) {
				stockPerformance.setStockSymbol(entry.getKey());				
				stockPerformance.setLastPrice(entry.getValue().getQuote().getPrice());
				// stockPerformance.setCostBasis(new BigDecimal(0));
				// stockPerformance.setMarketValue(new BigDecimal(0));

				BigDecimal buyPrice = stockBuyPriceMap.get(entry.getKey());
				
				BigDecimal currPrice = entry.getValue().getQuote().getPrice();
				
				BigDecimal gain = null;
				BigDecimal gainper = null;
				if (currPrice != null) {
					gain = currPrice.subtract(buyPrice);
					stockPerformance.setGain(gain);
					stockPerformance.setChange(entry.getValue().getQuote().getChange());
					gainper = (gain.divide(buyPrice,RoundingMode.HALF_UP).multiply(new BigDecimal(100)));
					stockPerformance.setGainPercentage(gainper);
				}												

				stockPerformanceList.add(stockPerformance);
			} else {
				log.error("Invalid stock symbol - " + entry.getKey());
			}
		}

		return stockPerformanceList;
	}

	public void addCash(int portfolioId, BigDecimal cash){
		
	}
}
