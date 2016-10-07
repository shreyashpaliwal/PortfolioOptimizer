package edu.njit.cs673.portfoliooptimizer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioDao;

@Service
public class PortfolioValidationServiceImpl implements PortfolioValidationService {

	private static final Logger log = Logger.getLogger(PortfolioValidationServiceImpl.class);

	private static final int NIFTY_50_STOCK_EXCHANGE_ID = 2;
	private static final int DOW_30_STOCK_EXCHANGE_ID = 1;

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

		List<PortfolioStock> stocks = portfolio.getStocks();

		for (PortfolioStock stock : stocks) {

			if (stock.getStockExchangeType().getStockExchangeId() == DOW_30_STOCK_EXCHANGE_ID) {
				dow30StockCount++;
			} else if (stock.getStockExchangeType().getStockExchangeId() == NIFTY_50_STOCK_EXCHANGE_ID) {
				nifty50StockCount++;
			}

		}
		
		
		log.debug("DOW 30 Stock count = " + dow30StockCount);
		log.debug("NIFTY 50 Stock count = " + nifty50StockCount);				

		double totalShareCount = dow30StockCount + nifty50StockCount;

		log.debug("Total Stock count = " + totalShareCount);
		
		double dow30SharePercentage = (dow30StockCount / totalShareCount) * 100;
		double nifty50SharePercentage = (nifty50StockCount / totalShareCount) * 100;

		
		log.debug("Dow 30 share percentage = " + dow30SharePercentage);
		log.debug("Nifty 50 share percentage = " + nifty50SharePercentage);
		
		int availBalance = Integer.parseInt(portfolio.getCashBalance().toString());

		if (availBalance > 10000) {
			
			log.debug("Portfolio unbalanced due to Cash more than 10000$ ");
			return false;
		} else if (Math.ceil(dow30SharePercentage) > 71 || Math.floor(nifty50SharePercentage) < 29) {
			log.debug("Portfolio unbalanced due to 70-30 rule failed ");
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

	@Override
	public List<PortfolioStock> getStocklist(int stockMatketIdex) {

		return null;
	}

}
