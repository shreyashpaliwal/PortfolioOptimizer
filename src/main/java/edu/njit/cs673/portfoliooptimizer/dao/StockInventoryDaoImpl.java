package edu.njit.cs673.portfoliooptimizer.dao;

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

import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.c673.portfoliooptimizer.model.StockInventory;

@Repository
@Transactional
public class StockInventoryDaoImpl implements StockInventoryDao {

	private static final Logger log = Logger.getLogger(InvestorDaoImpl.class);

	@Autowired
	private HibernateTemplate template;

	@Override
	public List<StockInventory> getAllStockInventory() {

		Query q = template.getSessionFactory().getCurrentSession().createQuery("from StockInventory");
	    //q.setFirstResult(0); // modify this to adjust paging
	    //q.setMaxResults(100);
	    
		
		
	    return (List<StockInventory>) q.getResultList();
	    
		/*List<StockInventory> stocksList = template.get(StockInventory.class);
		if (stocksList == null || stocksList.size() == 0) {
			log.info("Stock List Not fetched.- " + stocksList.size());
			return null;
		} else {
			return stocksList;
		}*/
	}

}
