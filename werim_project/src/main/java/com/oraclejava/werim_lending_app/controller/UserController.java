package com.oraclejava.werim_lending_app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.CustomUser;
import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.UserInfo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	
	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("userList", userInfoRepository.findByStoreState("1"));
		
		model.addAttribute("contents", 
				"user/userList :: userList_contents");
		
		return "user/userLayout";
		
	}
	@PostMapping(params = "update", value = "/userList")
	public String userListup(@AuthenticationPrincipal CustomUser user, UserInfo userInfo) {
		userInfoRepository.save(user.getUserinfo());
		System.out.println(userInfo);
		
		return "redirect:/user/userLayout";
		
	}
		@RequestMapping(value = "/userLayout", method = RequestMethod.GET)
		public ModelAndView userLayout() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("user/userLayout");
			
			mav.addObject("contents", 
					 null);
			return mav;
		}
		
		@RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
		public ModelAndView userUpdate() {
			ModelAndView mav = new ModelAndView();
			mav.setViewName("userUpdate");
			return mav;
		}
		
		//사용자수정(GET)
		@GetMapping("/userUpdate/{user_id}")
		public String getUser(@PathVariable Integer user_id, Model model) {
			UserInfo userInfo = userInfoRepository.findById(user_id).get();
			userInfo.setPassword(userInfo.getPassword().substring(6));
			model.addAttribute("userInfo", userInfo);
			
			model.addAttribute("contents", 
					"admin/userUpdate :: userUpdate_contents");
			
			return "admin/adminLayout";
		}
		
		@PostMapping(params = "update", value = "/userUpdate")
		public String userUpdate(@Validated UserInfo userInfo, 
				BindingResult bindingResult,
				Model model) {
			
			if (bindingResult.hasErrors()) {
				model.addAttribute("contents", 
						"admin/userUpdate :: userUpdate_contents");
				
				return "admin/adminLayout";
			}
			userInfo.setPassword("{noop}" + userInfo.getPassword());
			userInfoRepository.save(userInfo);
			
			return "redirect:/admin/userList";
			
		}
		@GetMapping("/userDelete")
		public String getUserd(Model model) {
			
			model.addAttribute("contents", 
					"user/userDelete :: userDelete_contents");
			return "user/userLayout";
		}
		@PostMapping(params = "delete", value = "/userDelete")
		public String userDelete(@AuthenticationPrincipal CustomUser user,HttpSession session) {
			userInfoRepository.deleteById(user.getUserinfo().getUser_id());
			session.invalidate();
			return "redirect:/";
		}
}	
