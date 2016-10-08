package edu.njit.cs673.portfoliooptimizer.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.c673.portfoliooptimizer.model.StockPerformance;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioDao;
import edu.njit.cs673.portfoliooptimizer.service.StockService;

@Service
public class PortfolioValidationServiceImpl implements PortfolioValidationService {

	private static final Logger log = Logger.getLogger(PortfolioValidationServiceImpl.class);

	private static final int NIFTY_50_STOCK_EXCHANGE_ID = 2;
	private static final int DOW_30_STOCK_EXCHANGE_ID = 1;

	@Autowired
	StockService stockService;
	
	@Override
	public boolean validatePortfolio(Portfolio portfolio) {

		if (portfolio == null || portfolio.getStocks() == null || portfolio.getStocks().size() == 0) {
			return false;
		}

		log.info("Validation portfolio - " + portfolio.getPortfolioId());

		// Portfolio portfolio = dao.getPortfolio();
		/*
		 * List<PortfolioStock> stockdow30 = null; stockdow30 = getStocklist(1);
		 * List<PortfolioStock> stocknifty50 = null; stocknifty50 =
		 * getStocklist(2);
		 */

		double dow30StockCount = 0;
		double nifty50StockCount = 0;
		
		double dow30SharePercentage = 0;
		double nifty50SharePercentage = 0;
		
		
		//for cash check of 30-70
		BigDecimal dow30cash = new BigDecimal(0);
		BigDecimal niftycash = new BigDecimal(0);

		List<PortfolioStock> stocks = portfolio.getStocks();
		List<StockPerformance> stockPerformance = null;
		try {
			stockPerformance = stockService.getStockPerformance(stocks);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (PortfolioStock stock : stocks) {

			if (stock.getStockExchangeType().getStockExchangeId() == DOW_30_STOCK_EXCHANGE_ID) {
				dow30StockCount++;
				for(StockPerformance stockperf : stockPerformance)
				{
					if(stock.getStockSymbol().equalsIgnoreCase(stockperf.getStockSymbol()))
					{
						dow30cash = dow30cash.add(stockperf.getCostBasis());
					}
				}
			} else if (stock.getStockExchangeType().getStockExchangeId() == NIFTY_50_STOCK_EXCHANGE_ID) {
				nifty50StockCount++;
				for(StockPerformance stockperf : stockPerformance)
				{
					if(stock.getStockSymbol().equalsIgnoreCase(stockperf.getStockSymbol())){
						niftycash = stockperf.getCostBasis();
					}
				}
			}

		}
		

		
		log.debug("DOW 30 cash total count = " + dow30cash);
		log.debug("NIFTY 50 cash total count = " + niftycash);	
		
		
		log.debug("DOW 30 Stock count = " + dow30StockCount);
		log.debug("NIFTY 50 Stock count = " + nifty50StockCount);	
		
		BigDecimal totalcash = dow30cash.add(niftycash);

		double totalShareCount = dow30StockCount + nifty50StockCount;

		log.debug("Total Stock count = " + totalShareCount);
		
		//double dow30SharePercentage = (dow30StockCount / totalShareCount) * 100;
		//double nifty50SharePercentage = (nifty50StockCount / totalShareCount) * 100;
		
		if((!(totalcash == null)) && !totalcash.equals(new BigDecimal(0)))
		{
			dow30SharePercentage = (dow30cash.doubleValue()/totalcash.doubleValue()) * 100;
		 	nifty50SharePercentage = (niftycash.doubleValue() / totalcash.doubleValue()) * 100;
		}
		
		log.debug("Dow 30 share percentage = " + dow30SharePercentage);
		log.debug("Nifty 50 share percentage = " + nifty50SharePercentage);
		
		int availBalance = Integer.parseInt(portfolio.getCashBalance().toString());

		if (availBalance > 10000) {
			
			log.debug("Portfolio unbalanced due to Cash more than 10000$ ");
			return false;
		} else if (Math.ceil(dow30SharePercentage) > 70 || Math.floor(nifty50SharePercentage) < 30) {
			log.debug("Portfolio unbalanced due to 70-30 rule failed ");
			return false;
		} else if (totalShareCount < 7 || totalShareCount > 9)
		{
			log.debug("Portfolio unbalanced due to min-max stocks");
			return false;
		} else {
			return true;
		}

		// int total = stockdow30.size() + stocknifty50.size();
		// float dow30Share = (stockdow30.size() / total) * 100;
		// float nif50Share = (stocknifty50.size() / total) * 100;

		/*
		 * if (dow30Share == 70.00 && nif50Share == 30.00 && availBalance <
		 * 1000) { return true; } else { return false; }
		 */
	}

	

}
