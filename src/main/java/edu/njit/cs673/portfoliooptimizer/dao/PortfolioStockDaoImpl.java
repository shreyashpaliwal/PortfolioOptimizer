package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioService;

@Repository
@Transactional
public class PortfolioStockDaoImpl implements PortfolioStockDao {

	private static final Logger log = Logger.getLogger(InvestorDaoImpl.class);

	@Autowired
	private PortfolioService portfolio;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate template;

	@Override
	public PortfolioStock getPortfoliostockByStockSymbol(String stockSymbol,int portfolioID) {

		@SuppressWarnings("unchecked")

		List<PortfolioStock> portfolioStock = (List<PortfolioStock>) template
				.findByCriteria(DetachedCriteria.forClass(PortfolioStock.class).add(Restrictions.eq("stockSymbol", stockSymbol)));
		
		for(int i = 0;i<portfolioStock.size();i++)
		{
			if(portfolioStock.get(i).getPortfolio().getPortfolioId()==portfolioID) 
				return portfolioStock.get(i);
			else
				return null;
		}
		
		if (portfolioStock == null || portfolioStock.isEmpty()) {
			log.info("No Stoclk found matching StockSymbol- " + stockSymbol);
			return null;
		} else {
			return portfolioStock.get(0);
		}
	}

	@Override
	public void addStocktoPortfolio(String stockSymbol, int shareQuantity, BigDecimal purchasePrice, int portfolioID) {

		String sql1 = "INSERT INTO PORTFOLIO_STOCK(PFL_STOCK_ID, PURCHASE_PRICE, SHARE_QUANTITY, STOCK_SYMBOL, PORTFOLIO_ID, STOCK_EXCHANGE_ID)values(portfolio_stock_seq.nextval, :stockSymbol,:shareQuantity,:purchasePrice,:Portfolio,1)";
		// String sql1 = "insert into PortfolioStock
		// (portfolioStockId,stockSymbol,shareQuantity,purchasePrice,Portfolio)
		// values(portfoliostock_seq.nextval,
		// :stockSymbol,:shareQuantity,:purchasePrice,:Portfolio)";
		Query sql = sessionFactory.getCurrentSession().createSQLQuery(sql1);
		sql.setParameter("stockSymbol", stockSymbol);
		sql.setParameter("shareQuantity", shareQuantity);
		sql.setParameter("purchasePrice", purchasePrice);
		sql.setParameter("Portfolio", portfolioID);
		sql.executeUpdate();

	}

}
