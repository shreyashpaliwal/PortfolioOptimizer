package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;
import java.util.List;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;

public interface PortfolioDao {

	public Portfolio getPortfolioById(int portfolioId);
	
	public List<PortfolioStock> getStocksByPortfolio(int portfolioId);
	
	public void addCash(int portfolioId, BigDecimal cash);
}
