package edu.njit.cs673.portfoliooptimizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BuyShareController {

	@RequestMapping(value = "/BuyShare.htm", method = RequestMethod.GET)
	public ModelAndView BuyShare(@RequestParam(name = "portfolioId", required = true) int portfolioId) {

		ModelAndView model = new ModelAndView("BuyShare");
		model.addObject("portfolioId", portfolioId);
		return model;
	}
}
