package com.oraclejava.werim_lending_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.UserInfo;

@Controller
public class UserController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	//사용자수정(GET)
		@GetMapping("/userUpdate/{user_id}")
		public String getUser(@PathVariable Integer user_id, Model model) {
			UserInfo userInfo = userInfoRepository.findById(user_id).get();
			userInfo.setPassword(userInfo.getPassword().substring(6));
			model.addAttribute("userInfo", userInfo);
			
			model.addAttribute("contents", 
					"userUpdate :: userUpdate_contents");
			
			return "userLayout";
		}
		
		//사용자수정(POST)
		@PostMapping(params = "update", value = "/userUpdate")
		public String userUpdate(@Validated UserInfo userInfo, 
				BindingResult bindingResult,
				Model model) {
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("contents", 
						"userUpdate :: userUpdate_contents");
				
				return "userLayout";
			}
			userInfo.setPassword("{noop}" + userInfo.getPassword());
			userInfoRepository.save(userInfo);
			
			return "redirect:/userLayout";
			
		}	
		
}	
