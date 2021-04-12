package com.oraclejava.werim_lending_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/chicken", method = RequestMethod.GET)
	public ModelAndView chicken() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chicken");
		return mav;
	}

	@RequestMapping(value = "/pizza", method = RequestMethod.GET)
	public ModelAndView pizza() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pizza");
		return mav;
	}
	
	@RequestMapping(value = "/middlefood", method = RequestMethod.GET)
	public ModelAndView middlefood() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("middlefood");
		return mav;
	}
}
