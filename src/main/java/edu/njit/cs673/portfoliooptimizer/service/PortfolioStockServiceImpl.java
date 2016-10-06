package edu.njit.cs673.portfoliooptimizer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioStockDao;

@Transactional
@Service
public class PortfolioStockServiceImpl implements PortfolioStockService{
	
	@Autowired
	PortfolioStockDao portfoliostockservice;
	
	@Override
	public PortfolioStock getPortfoliostockByStockSymbol(String stockSymbol) {
		
		return portfoliostockservice.getPortfoliostockByStockSymbol(stockSymbol);
	}

	@Override
	public void addStocktoPortfolio(String stockSymbol, int shareQuantity, BigDecimal purchasePrice, int portfolioID) {
		// TODO Auto-generated method stub
		portfoliostockservice.addStocktoPortfolio(stockSymbol,shareQuantity,purchasePrice,portfolioID);
	}

}
