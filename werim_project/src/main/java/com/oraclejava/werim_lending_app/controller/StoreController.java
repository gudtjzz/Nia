package com.oraclejava.werim_lending_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oraclejava.werim_lending_app.CustomUser;
import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.UserInfo;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private UserInfoRepository userinfoRepository;
	
	@GetMapping("/request")
	public String requestStore() {
		return "/store/request";
	}
	
	@PostMapping("/request")
	public String requestStore(@AuthenticationPrincipal CustomUser user) {
		System.out.println("post");
		UserInfo userinfo = user.getUserinfo();
		userinfo.setStore_state("1");
		
		userinfoRepository.save(userinfo);
		
		return "redirect:/store/request";
	}
	
	@GetMapping("/register")
	public String register() {
		return "/store/register";
	}
}
