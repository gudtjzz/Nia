package com.oraclejava.werim_lending_app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.CustomUser;
import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.UserInfo;

import ch.qos.logback.core.encoder.Encoder;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
<<<<<<< HEAD
	private UserInfoRepository userInfoRepository;

	@Autowired
	private BCryptPasswordEncoder encoder;





	@RequestMapping(value = "/userLayout", method = RequestMethod.GET)
	public ModelAndView userLayout() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("user/userLayout");

		mav.addObject("contents", 
				null);
		return mav;
	}

	@RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
	public ModelAndView userUpdate(@AuthenticationPrincipal CustomUser user) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "user/userUpdate :: userUpdate_contents");
		UserInfo userInfo = userInfoRepository.findByUsername(user.getUserinfo().getUsername());
		mav.setViewName("user/userLayout");
		mav.addObject("userInfo",userInfo);
		return mav;
	}
	
	@RequestMapping(value = "/userpwUpdate", method = RequestMethod.GET)
	public ModelAndView userpwUpdate(@AuthenticationPrincipal CustomUser user) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("contents",  "user/userpwUpdate :: userpwUpdate_contents");
		UserInfo userInfo = userInfoRepository.findByUsername(user.getUserinfo().getUsername());
		mav.setViewName("user/userLayout");
		mav.addObject("userInfo",userInfo);
		return mav;
	}
=======
	private BCryptPasswordEncoder encoder;
	
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
	public String userListup(@AuthenticationPrincipal CustomUser user, @RequestParam("user_id") List<Integer> user_id) {

	    userInfoRepository.findAllById(user_id);
		 List<UserInfo> user_list = (List<UserInfo>)userInfoRepository.findAllById(user_id);
	      user_list.forEach(userinfo -> ((UserInfo) userinfo).setStoreState("2"));
	      userInfoRepository.saveAll(user_list);
		
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
		public ModelAndView userUpdate(@AuthenticationPrincipal CustomUser user) {
			ModelAndView mav = new ModelAndView();
			mav.addObject("contents",  "user/userUpdate :: userUpdate_contents");
			UserInfo userInfo = userInfoRepository.findByUsername(user.getUserinfo().getUsername());
			mav.setViewName("user/userLayout");
			mav.addObject("userInfo",userInfo);
			return mav;
		}
		
		@RequestMapping(params ="update", value="/userUpdate", method=RequestMethod.POST)
		public String userUpdate2(UserInfo user,@AuthenticationPrincipal CustomUser user2) {
			user.setPassword(encoder.encode(user.getPassword()));
			user.setUser_id(user2.getUserinfo().getUser_id());
			user2.setUserinfo(user);
			userInfoRepository.save(user);
			return "redirect:/user/userLayout";
		}
>>>>>>> origin/werim
	
	@RequestMapping(params ="update", value="/userUpdate", method=RequestMethod.POST)
	public String userUpdate2(UserInfo user,@AuthenticationPrincipal CustomUser user2) {
		user.setPassword(encoder.encode(user.getPassword()));
		user.setUser_id(user2.getUserinfo().getUser_id());
		user2.setUserinfo(user);
		userInfoRepository.save(user);
		return "redirect:/user/userLayout";
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
