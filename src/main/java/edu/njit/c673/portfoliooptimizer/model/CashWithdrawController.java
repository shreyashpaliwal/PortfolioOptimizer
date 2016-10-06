package edu.njit.c673.portfoliooptimizer.model;

import java.math.BigDecimal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.njit.cs673.portfoliooptimizer.controller.CashTransactionController;
import edu.njit.cs673.portfoliooptimizer.service.PortfolioService;


@Controller
public class CashWithdrawController {
	
private static final Logger log = Logger.getLogger(CashTransactionController.class);
	
	@Autowired
	PortfolioService portfolioService;
	
	
	@RequestMapping(name="/removeCash.htm", method=RequestMethod.GET)
	public ModelAndView removeCash(@RequestParam int portfolioId, @RequestParam int cashAmount, HttpSession session){
		
		session.getAttribute("viewPortfolio");
		
		ModelAndView model = new ModelAndView("viewPortfolio");
		
		portfolioService.removeCash(portfolioId, new BigDecimal(cashAmount));
		
		log.debug("Getting portfolio Stocks for portfolio ID - " + portfolioId);
		
		return model;
	}

}
