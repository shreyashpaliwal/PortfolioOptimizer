package edu.njit.cs673.portfoliooptimizer.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;

@Service
public class PortfolioValidationServiceImpl implements PortfolioValidationService {

	private static final Logger log = Logger.getLogger(PortfolioValidationServiceImpl.class);

	@Override
	public boolean validatePortfolio(int portfolioId) {

		log.info("Validation portfolio - " + portfolioId);

		Portfolio portfolio = null;
		// Portfolio portfolio = dao.getPortfolio();
		List<PortfolioStock> stockdow30 = null;
		stockdow30 = getStocklist(1);
		List<PortfolioStock> stocknifty50 = null;
		stocknifty50 = getStocklist(2);
		int total = stockdow30.size() + stocknifty50.size();
		float dow30Share = (stockdow30.size() / total) * 100;
		float nif50Share = (stocknifty50.size() / total) * 100;
		int availBalance = Integer.parseInt(portfolio.getCashBalance().toString());
		if (dow30Share == 70.00 && nif50Share == 30.00 && availBalance < 1000) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<PortfolioStock> getStocklist(int stockMatketIdex) {
		// TODO Auto-generated method stub
		return null;
	}

}
