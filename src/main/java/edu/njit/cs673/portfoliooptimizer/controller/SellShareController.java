package edu.njit.cs673.portfoliooptimizer.controller;

import org.springframework.stereotype.Controller;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SellShareController {
	
	Logger log = Logger.getLogger(SellShareController.class);
	
	@RequestMapping(value = "/SellShare.htm", method = RequestMethod.GET)
	public ModelAndView sellShare(@RequestParam(name = "portfolioId", required = true) int portfolioId) {

		ModelAndView model = new ModelAndView("SellShare");
		model.addObject("portfolioId", portfolioId);
		return model;
	}

}
