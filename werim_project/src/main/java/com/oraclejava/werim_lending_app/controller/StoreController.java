package com.oraclejava.werim_lending_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String requestStore() {
		return "/store/request";
	}
	
	@PostMapping("/request")
	public String requestStore(@AuthenticationPrincipal CustomUser user) {
		UserInfo userinfo = user.getUserinfo();
		userinfo.setStore_state("1");
		
		userinfoRepository.save(userinfo);
		
		return "redirect:/store/request";
	}
	
	@GetMapping("/register")
	public String register(Model model, @AuthenticationPrincipal CustomUser user) {
		if( !model.containsAttribute("store")) {
			Store store = new Store();
			store.setUser_id(user.getUserinfo().getUser_id());
			model.addAttribute("store", store );
		}
		List<Franchise> fran_list = (List<Franchise>) franchiseRepository.findAll();
		model.addAttribute("fran_list",fran_list);
		model.addAttribute("userinfo", user.getUserinfo());
		
		return "/store/register";
	}
	
	@PostMapping("/register")
	public String register(Store store) {
		storeRepository.save(store);
		return "redirect:/user/userLayout";
	}
}
