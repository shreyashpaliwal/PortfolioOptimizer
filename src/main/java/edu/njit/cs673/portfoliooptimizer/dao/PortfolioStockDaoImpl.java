package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;
import java.util.Arrays;
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
import edu.njit.c673.portfoliooptimizer.model.StockExchangeType;
import edu.njit.c673.portfoliooptimizer.model.StockInventory;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioService;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioStockService;
import edu.njit.cs673.portfoliooptimizer.service.StockService;

@Repository
@Transactional
public class PortfolioStockDaoImpl implements PortfolioStockDao {

	private static final Logger log = Logger.getLogger(InvestorDaoImpl.class);

	@Autowired
	StockService stockService;
	@Autowired
	private PortfolioService portfolio;

	@Autowired
	private PortfolioStockService portfolioStockService;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate template;

	@Override
	public PortfolioStock getPortfoliostockByStockSymbol(String stockSymbol,int portfolioID) {

		@SuppressWarnings("unchecked")

		DetachedCriteria criteria = DetachedCriteria.forClass(PortfolioStock.class);
		criteria.add(Restrictions.eq("stockSymbol", stockSymbol));
		criteria.add(Restrictions.eq("portfolio",portfolio.getPortfolioById(portfolioID)));
		
		List<PortfolioStock> portfolioStock = (List<PortfolioStock>) template
				.findByCriteria(criteria);
		
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

		
		PortfolioStock portfolioStock = new PortfolioStock();
		
		portfolioStock.setPortfolio(portfolio.getPortfolioById(portfolioID));
		portfolioStock.setPurchasePrice(purchasePrice);
		portfolioStock.setShareQuantity(new BigDecimal(shareQuantity));
		portfolioStock.setStockSymbol(stockSymbol);
		List<StockInventory> stocks = stockService.getStockFromInventory();
		for (StockInventory stockInventory : stocks) {
			
			if(stockInventory.getStockSymbol().equals(stockSymbol))
			{
				portfolioStock.setStockExchangeType(stockInventory.getStockExchangeType());
				break;
			}
		}
		template.save(portfolioStock);
	}

	public void updateStocktoPortfolio(String stockSymbol,int shareQuantity,BigDecimal purchasePrice,int portfolioID)
	{
		PortfolioStock stock = portfolioStockService.getPortfoliostockByStockSymbol(stockSymbol, portfolioID);
		BigDecimal i= stock.getShareQuantity().add(new BigDecimal(shareQuantity));
		stock.setShareQuantity(i);
		stock.setPurchasePrice(purchasePrice);
		template.save(stock);
	}
	
	public void sellStockPortfolio(String stockSymbol,int shareQuantity,BigDecimal sellPrice,int portfolioID)
	{
		List<PortfolioStock> stocks = portfolio.getStocksByPortfolio(portfolioID);
		
		PortfolioStock stock = getPortfoliostockByStockSymbol(stockSymbol,portfolioID);
		int updatedStockQuantity =0;
		for(int i = 0;i<stocks.size();i++)
		{
			if(stocks.get(i).getStockSymbol() == stockSymbol) 
				updatedStockQuantity = Integer.parseInt(stock.getShareQuantity().toString()) - shareQuantity;
			else
				updatedStockQuantity = shareQuantity;
		}
		
		
		String sql1 = "update PORTFOLIO_STOCK set SHARE_QUANTITY = :shareQuantity where ("
				+ "STOCK_SYMBOL = :stockSymbol and  "
				+ "PORTFOLIO_ID = :Portfolio"				
				+ ")";
		// String sql1 = "insert into PortfolioStock
		// (portfolioStockId,stockSymbol,shareQuantity,purchasePrice,Portfolio)
		// values(portfoliostock_seq.nextval,
		// :stockSymbol,:shareQuantity,:purchasePrice,:Portfolio)";
		Query sql = sessionFactory.getCurrentSession().createSQLQuery(sql1);
		sql.setParameter("shareQuantity", shareQuantity);
		sql.setParameter("stockSymbol", stockSymbol);
		sql.setParameter("Portfolio", portfolioID);
		sql.executeUpdate();
	}
}
