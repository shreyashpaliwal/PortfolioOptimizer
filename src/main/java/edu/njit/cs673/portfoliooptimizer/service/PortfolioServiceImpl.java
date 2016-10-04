package edu.njit.cs673.portfoliooptimizer.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.cs673.portfoliooptimizer.dao.PortfolioDao;

@Transactional
@Service
public class PortfolioServiceImpl implements PortfolioService {

	@Autowired
	PortfolioDao portfolioDao;

	@Override
	public List<PortfolioStock> getStocksByPortfolio(int portfolioId) {

		return portfolioDao.getStocksByPortfolio(portfolioId);

	}

	@Override
	public void addCash(int portfolioId, BigDecimal cash) {

		portfolioDao.addCash(portfolioId, cash);
		
	}

	public Portfolio getPortfolioById(int portfolioId){
		
		return portfolioDao.getPortfolioById(portfolioId);
		
	}
}
