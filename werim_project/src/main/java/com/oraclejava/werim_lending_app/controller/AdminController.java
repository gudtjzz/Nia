package com.oraclejava.werim_lending_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oraclejava.werim_lending_app.dao.UserInfoRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("userList", userInfoRepository.findAll());
		
		return "admin/userList";
		
	}

}
