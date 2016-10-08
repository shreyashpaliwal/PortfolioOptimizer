package edu.njit.cs673.portfoliooptimizer.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
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

import edu.njit.c673.portfoliooptimizer.model.Investor;
import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.c673.portfoliooptimizer.model.PortfolioStock;
import edu.njit.c673.portfoliooptimizer.model.StockInventory;
import edu.njit.c673.portfoliooptimizer.model.StockPerformance;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioService;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioStockService;
import edu.njit.cs673.portfoliooptimizer.service.StockService;
import yahoofinance.Stock;
import yahoofinance.YahooFinance;

@Transactional
@Controller
public class SellShareController {

	

	@Autowired
	StockService stockService;
	
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	PortfolioStockService portfoliostockService;
	
	Logger log = Logger.getLogger(SellShareController.class);
	
	@RequestMapping(value = "/SellShare.htm", method = RequestMethod.GET)
	public ModelAndView sellShare(@RequestParam(name = "portfolioId", required = true) int portfolioId) {
		
		List<String> errorMessages = new ArrayList<String>();
		HashSet<String> stockset = new HashSet<String>();
		ModelAndView model = new ModelAndView("SellShare");
		List<StockInventory> stocks = stockService.getStockFromInventory();
		List <PortfolioStock> portstocks = portfolioService.getStocksByPortfolio(portfolioId);
		if(portfolioId == 0 || stocks.isEmpty())
		{
			errorMessages.add("stocks or portfolio id is missing");
		}
		
		if(!portstocks.isEmpty()){
			for (PortfolioStock port : portstocks)
			{
				stockset.add(port.getStockSymbol());
			}
		}
		model.addObject("portfolioId", portfolioId);
		model.addObject("stocks", stocks);
		model.addObject("portstocks", stockset);
		model.addObject("errorMessages",errorMessages);
		return model;
	}

}
