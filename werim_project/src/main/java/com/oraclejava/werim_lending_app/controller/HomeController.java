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
	
	@RequestMapping(value = "/koreanfood", method = RequestMethod.GET)
	public ModelAndView koreanfood() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("koreanfood");
		return mav;
	}
	
	@RequestMapping(value = "/japanesefood", method = RequestMethod.GET)
	public ModelAndView japanesefood() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("japanesefood");
		return mav;
	}
	
	@RequestMapping(value = "/jokbo", method = RequestMethod.GET)
	public ModelAndView jokbo() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("jokbo");
		return mav;
	}
	
	@RequestMapping(value = "/yasik", method = RequestMethod.GET)
	public ModelAndView yasik() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("yasik");
		return mav;
	}
	
	@RequestMapping(value = "/boonsik", method = RequestMethod.GET)
	public ModelAndView boonsik() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boonsik");
		return mav;
	}
	
	@RequestMapping(value = "/cafe", method = RequestMethod.GET)
	public ModelAndView cafe() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cafe");
		return mav;
	}
	
	
}
