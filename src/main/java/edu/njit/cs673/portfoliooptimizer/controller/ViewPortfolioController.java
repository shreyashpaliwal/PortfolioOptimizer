package edu.njit.cs673.portfoliooptimizer.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.njit.cs673.portfoliooptimizer.service.StockService;
import yahoofinance.Stock;

@Controller
public class ViewPortfolioController {

	Logger logger = Logger.getLogger(ViewPortfolioController.class);

	@Autowired
	StockService stockService;

	@RequestMapping(value = "/~snp59/webapps7/getStockDetails.htm", method = RequestMethod.GET)
	public @ResponseBody Map<String, Stock> getStockDetails(
			@RequestParam(name = "stockSymbols", required = true) String symbols) {

		String[] symbolArray = symbols.split(",");
		logger.debug("Requesting quote for:: " + StringUtils.arrayToCommaDelimitedString(symbolArray));

		return stockService.getStockQuotes(symbolArray);

	}
}
