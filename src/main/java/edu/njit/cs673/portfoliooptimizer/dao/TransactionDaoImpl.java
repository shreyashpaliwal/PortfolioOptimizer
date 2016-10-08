package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.Transaction;
import edu.njit.c673.portfoliooptimizer.model.TransactionType;
@Repository
@Transactional
public class TransactionDaoImpl implements TransactionDao{

	@Autowired
	private HibernateTemplate template;
	
	@Override
	public void addTransaction(Portfolio portfolio, TransactionType transactionType, Date transactionDate,
			BigDecimal transactionAmount, int shareQuantity, BigDecimal unitSharePrice) {
		// TODO Auto-generated method stub
	Transaction transaction = new Transaction();
	transaction.setPortfolio(portfolio);
	transaction.setTransactionType(transactionType);
	transaction.setTransactionDate(transactionDate);
	transaction.setShareQuantity(shareQuantity);
	transaction.setTransactionAmount(transactionAmount);
	transaction.setUnitSharePrice(unitSharePrice);
	
	template.save(transaction);
		
	}

}
