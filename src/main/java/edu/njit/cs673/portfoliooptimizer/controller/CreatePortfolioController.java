package edu.njit.cs673.portfoliooptimizer.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.njit.c673.portfoliooptimizer.model.Investor;
import edu.njit.c673.portfoliooptimizer.model.Portfolio;
import edu.njit.cs673.portfoliooptimizer.exception.AuthenticationException;
import edu.njit.cs673.portfoliooptimizer.service.LoginService;
import edu.njit.cs673.portfoliooptimizer.vo.InvestorProfileVO;

@Controller
public class CreatePortfolioController {

	private static final Logger log = Logger.getLogger(InvestorLoginController.class);

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/CreatePortfolio.htm", method = RequestMethod.POST)
	public ModelAndView CreatePortfolio(Portfolio portfolio) {

		ModelAndView model = new ModelAndView("viewPortfolio");

		model.addObject("investorProfileVO", null);

		log.debug("User " + portfolio.getPortfolioName() + " trying to log in.");

		Investor investor = null;

		Set<String> errors = new HashSet<String>();
		List<String> portfolioSet = new ArrayList<String>();

		InvestorProfileVO vo = new InvestorProfileVO();

		/*try {
			investor = loginService.authenticate(username, password);
		} catch (AuthenticationException e) {
			errors.add(e.toString());
			return model;
		}*/

		vo.setFirstName(investor.getFirstName());
		vo.setLastName(investor.getLastName());
		vo.setInvestorId(investor.getInvestorId());
		vo.setUsername(investor.getUserName());
/*
		for (Portfolio portfolio : investor.getPortfolios()) {
			portfolioSet.add(portfolio.getPortfolioName());
		}
		vo.setPortfolios(portfolioSet);

		log.debug("User " + username + " logged in successfully.");
*/
		model.addObject("investorProfileVO", vo);
		return model;
	}
	
}
