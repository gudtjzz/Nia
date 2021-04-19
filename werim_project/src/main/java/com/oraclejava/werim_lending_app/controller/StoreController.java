package com.oraclejava.werim_lending_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.CustomUser;
import com.oraclejava.werim_lending_app.dao.FranchiseRepository;
import com.oraclejava.werim_lending_app.dao.StoreRepository;
import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.Franchise;
import com.oraclejava.werim_lending_app.dto.Store;
import com.oraclejava.werim_lending_app.dto.UserInfo;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private UserInfoRepository userinfoRepository;
	
	@Autowired
	private FranchiseRepository franchiseRepository;
	
	@Autowired
	private StoreRepository storeRepository;
	
	@GetMapping("/request")
	public ModelAndView requestStore(ModelAndView mav) {
		mav.setViewName("/user/userLayout");
		mav.addObject("contents", "/user/store/request :: store-request-page");
		return mav;
	}
	
	@PostMapping("/request")
	public String requestStore(@AuthenticationPrincipal CustomUser user) {
		UserInfo userinfo = user.getUserinfo();
		userinfo.setStore_state("1");
		
		userinfoRepository.save(userinfo);
		
		return "redirect:/store/request";
	}
	
	@GetMapping("/register")
	public ModelAndView register(ModelAndView mav, @AuthenticationPrincipal CustomUser user) {
		if( !mav.getModelMap().containsAttribute("store")) {
			Store store = new Store();
			store.setUser_id(user.getUserinfo().getUser_id());
			mav.addObject("store", store );
		}
		List<Franchise> fran_list = (List<Franchise>) franchiseRepository.findAll();
		mav.addObject("fran_list",fran_list);
		mav.addObject("userinfo", user.getUserinfo());
		mav.addObject("contents", "user/store/register :: store-register-page");
		mav.setViewName("/user/store/register");
		
		return mav;
	}
	
	@PostMapping("/register")
	public String register(Store store) {
		storeRepository.save(store);
		return "redirect:/user/userLayout";
	}
}
