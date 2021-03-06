package com.oraclejava.werim_lending_app.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.dao.ImgFileRepository;
import com.oraclejava.werim_lending_app.dao.StoreRepository;



@Controller
public class HomeController {

	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ImgFileRepository imgFileRepository;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView homeLayout() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("contents",  "index :: index_contents");
		mav.addObject("contents",  null);
		return mav;
	}

	@RequestMapping(value = "/chicken", method = RequestMethod.GET)
	public ModelAndView chicken() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "chicken :: chicken_contents");
		mav.addObject("Store" , storeRepository.findByCategory("0"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	@RequestMapping(value = "/pizza", method = RequestMethod.GET)
	public ModelAndView pizza() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "pizza :: pizza_contents");
		mav.addObject("Store" , storeRepository.findByCategory("1"));
		
		mav.setViewName("homeLayout");
		return mav;
	}
	
	@RequestMapping(value = "/middlefood", method = RequestMethod.GET)
	public ModelAndView middlefood() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "middlefood :: middlefood_contents");
		mav.addObject("Store" , storeRepository.findByCategory("2"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	
	@RequestMapping(value = "/koreanfood", method = RequestMethod.GET)
	public ModelAndView koreanfood() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "koreanfood :: koreanfood_contents");
		mav.addObject("Store" , storeRepository.findByCategory("3"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	
	@RequestMapping(value = "/japanesefood", method = RequestMethod.GET)
	public ModelAndView japanesefood() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "japanesefood :: japanesefood_contents");
		mav.addObject("Store" , storeRepository.findByCategory("4"));
		mav.setViewName("homeLayout");
		return mav;
	}

	
	@RequestMapping(value = "/jokbo", method = RequestMethod.GET)
	public ModelAndView jokbo() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "jokbo :: jokbo_contents");
		mav.addObject("Store" , storeRepository.findByCategory("5"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	
	@RequestMapping(value = "/yasik", method = RequestMethod.GET)
	public ModelAndView yasik() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "yasik :: yasik_contents");
		mav.addObject("Store" , storeRepository.findByCategory("6"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	@RequestMapping(value = "/boonsik", method = RequestMethod.GET)
	public ModelAndView boonsik() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "boonsik :: boonsik_contents");
		mav.addObject("Store" , storeRepository.findByCategory("7"));
		mav.setViewName("homeLayout");
		return mav;
	}
	
	@RequestMapping(value = "/cafe", method = RequestMethod.GET)
	public ModelAndView cafe() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "cafe :: cafe_contents");
		mav.addObject("Store" , storeRepository.findByCategory("8"));
		mav.setViewName("homeLayout");
		return mav;
	}
	

	
	
	
}
