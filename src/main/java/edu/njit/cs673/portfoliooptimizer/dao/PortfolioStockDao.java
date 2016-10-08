package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;


public interface PortfolioStockDao {
	
	public PortfolioStock getPortfoliostockByStockSymbol(String stockSymbol,int portfolioID);
	
	public void addStocktoPortfolio(String stockSymbol,int shareQuantity,BigDecimal purchasePrice,int portfolioID);
	
	public void sellStockPortfolio(String stockSymbol,int shareQuantity,BigDecimal sellPrice,int portfolioID);
	
	public void updateStocktoPortfolio(String stockSymbol,int shareQuantity,BigDecimal purchasePrice,int portfolioID);
}
