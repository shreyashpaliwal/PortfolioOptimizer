package edu.njit.cs673.portfoliooptimizer.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.TransactionType;
import edu.njit.cs673.portfoliooptimizer.dao.TransactionDao;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionDao transactionService;

	@Override
	public void addTransaction(Portfolio portfolio, TransactionType transactionType, Date transactionDate,
			BigDecimal transactionAmount, int shareQuantity, BigDecimal unitSharePrice, String stockSymbol) {

		transactionService.addTransaction(portfolio, transactionType, transactionDate, transactionAmount, shareQuantity,
				unitSharePrice, stockSymbol);
	}

}
