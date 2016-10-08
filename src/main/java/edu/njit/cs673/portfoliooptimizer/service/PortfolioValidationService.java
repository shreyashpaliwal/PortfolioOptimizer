package edu.njit.cs673.portfoliooptimizer.service;

import java.util.List;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;

public interface PortfolioValidationService {

	public boolean validatePortfolio(Portfolio portfolio);
	
	public List<PortfolioStock> getStocklist(int stockMatketIdex);
	
}
