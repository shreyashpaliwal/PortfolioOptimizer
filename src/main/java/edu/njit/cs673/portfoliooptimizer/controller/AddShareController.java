package edu.njit.cs673.portfoliooptimizer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.c673.portfoliooptimizer.model.StockPerformance;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioService;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioStockService;
import edu.njit.cs673.portfoliooptimizer.service.StockService;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Transactional
@Controller
public class AddShareController {
	Logger log = Logger.getLogger(AddShareController.class);

	@Autowired
	StockService stockService;
	
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	PortfolioStockService portfoliostockService;
	
	
	
	@RequestMapping(value = "/addShare.htm", method = RequestMethod.POST)
	public ModelAndView addShare(@RequestParam(name = "hdportfolioId") int hdportfolioId,
			@RequestParam(name = "stocklist") String stockSymbol, @RequestParam(name = "shareQuantity") int shareQuantity, HttpSession session) throws IOException {

		
		PortfolioStock portfoliostock = portfoliostockService.getPortfoliostockByStockSymbol(stockSymbol);
		
			Stock stock = YahooFinance.get(stockSymbol);			 
			BigDecimal price = stock.getQuote().getPrice();
			portfoliostockService.addStocktoPortfolio(stockSymbol, shareQuantity, price, hdportfolioId);
					
		Portfolio portfolio = portfolioService.getPortfolioById(hdportfolioId);
		session.setAttribute("portfolio", portfolio);

		ModelAndView model = new ModelAndView("viewPortfolio");

		log.debug("Getting portfolio Stocks for portfolio ID - " + hdportfolioId);

		List<PortfolioStock> stocks = portfolioService.getStocksByPortfolio(hdportfolioId);

		// model.addObject("portfolioStocks", stocks);

		List<StockPerformance> performanceMatrix = null;

		if (stocks != null && stocks.size() > 0) {
			try {
				performanceMatrix = stockService.getStockPerformance(stocks);
			} catch (IOException e) {
				log.error("Performance matrices could not be fetched.");
			}

		}

		model.addObject("performanceMatrix", performanceMatrix);

		return model;
	}

}
