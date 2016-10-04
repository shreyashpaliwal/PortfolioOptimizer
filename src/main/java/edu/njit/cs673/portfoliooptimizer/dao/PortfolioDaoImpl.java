package edu.njit.cs673.portfoliooptimizer.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;

@Repository
@Transactional
public class PortfolioDaoImpl implements PortfolioDao {

	private static final Logger log = Logger.getLogger(PortfolioDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private HibernateTemplate template;

	@Override
	public List<PortfolioStock> getStocksByPortfolio(int portfolioId) {

		log.debug("getting stocks under portfolio: " + portfolioId);

		Portfolio portfolio = template.get(Portfolio.class, portfolioId);

		if (portfolio == null) {
			log.debug("Invalid portfolio ID - " + portfolioId);
			return null;
		}

		return portfolio.getStocks();

	}

	@Override
	public void addCash(int portfolioId, BigDecimal cash) {

		Portfolio portfolio = template.get(Portfolio.class, portfolioId);

		portfolio.setCashBalance(cash);

		template.save(portfolio);

	}

	public Portfolio getPortfolioById(int portfolioId) {

		return template.get(Portfolio.class, portfolioId);

	}
}
