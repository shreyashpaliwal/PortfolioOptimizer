package edu.njit.cs673.portfoliooptimizer.service;

import java.util.List;

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;

public interface PortfolioValidationService {

	public boolean validatePortfolio(int portfolioId);
	
	public List<PortfolioStock> getStocklist(int stockMatketIdex);
	
}
