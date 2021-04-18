package com.oraclejava.werim_lending_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.dto.UserInfo;
import com.oraclejava.werim_lending_app.service.UserManagerService;

@Controller
public class LoginController {

	@Autowired
	private UserManagerService userMService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;

	}
	@GetMapping("/logout")
	public String dispLogout() {
		return "/logout";
	}

	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		if(!model.containsAttribute("userInfo")) {
			UserInfo userInfo = new UserInfo();
			model.addAttribute("userInfo",userInfo);
		}
		
		return "registration";
	}

	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String createNewUser(@Validated UserInfo userInfo, BindingResult bindingResult, Model model) {

		UserInfo userExists = userMService.getUserInfo(userInfo.getUsername());

		if(userExists != null) {
			bindingResult.rejectValue("username", "error,user", "아이디가 중복되었습니다");
		}
		if(!userInfo.getPassword().equals(userInfo.getPassword2())) {
			bindingResult.rejectValue("password2", "error.password", "패스워드가 일치하지 않습니다");
		}

		if(bindingResult.hasErrors()) {
			return "registration";
		} else {
			try {
			userMService.register(userInfo);
			}catch(Exception e) {
				e.printStackTrace();
				return "registration";
			}
			return "redirect:/login";
		}
	}
	

}