package com.oraclejava.werim_lending_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/store")
public class StoreController {

	@GetMapping("/request")
	public String requestStore() {
		return "/store/request";
	}
	
	@PostMapping("/request")
	public String requestStore(String ff) {
		System.out.println("post");
		return "redirect:/store/request";
	}
}
