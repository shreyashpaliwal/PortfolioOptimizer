package edu.njit.cs673.portfoliooptimizer.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioDao;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioStockDao;

@Transactional
@Service
public class PortfolioStockServiceImpl implements PortfolioStockService{
	
	@Autowired
	PortfolioStockDao portfoliostockservice;
	
	@Autowired
	PortfolioDao portfolioDao;
	
	@Override
	public PortfolioStock getPortfoliostockByStockSymbol(String stockSymbol,int portfolioID) {
		
		return portfoliostockservice.getPortfoliostockByStockSymbol(stockSymbol,portfolioID);
	}

	@Override
	public void addStocktoPortfolio(String stockSymbol, int shareQuantity, BigDecimal purchasePrice, int portfolioID) {
		// TODO Auto-generated method stub
		portfoliostockservice.addStocktoPortfolio(stockSymbol,shareQuantity,purchasePrice,portfolioID);
		
		portfolioDao.addCash(portfolioID, purchasePrice.multiply(new BigDecimal(shareQuantity)), true);
		
	}

}
