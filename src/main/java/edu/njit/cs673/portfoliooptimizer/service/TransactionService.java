package edu.njit.cs673.portfoliooptimizer.service;

import java.math.BigDecimal;
import java.util.Date;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.TransactionType;

public interface TransactionService {

	public void addTransaction(Portfolio portfolio, TransactionType transactionType, Date transactionDate,
			BigDecimal transactionAmount, int shareQuantity, BigDecimal unitSharePrice);
}
