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
public class BuyShareController {

	Logger log = Logger.getLogger(ViewPortfolioController.class);

	@Autowired
	StockService stockService;
	
	@Autowired
	PortfolioService portfolioService;
	
	@Autowired
	PortfolioStockService portfoliostockService;
	
	@RequestMapping(value = "/BuyShare.htm", method = RequestMethod.POST)
	public ModelAndView BuyShare(@RequestParam(name = "portfolioId", required = true) int portfolioId) {

		ModelAndView model = new ModelAndView("BuyShare");
		
		List<StockInventory> stocks = stockService.getStockFromInventory();
		model.addObject("portfolioId", portfolioId);
		model.addObject("stocks", stocks);

		return model;
	}
	
}
