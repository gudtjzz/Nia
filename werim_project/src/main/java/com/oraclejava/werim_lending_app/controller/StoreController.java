package com.oraclejava.werim_lending_app.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.werim_lending_app.CustomUser;
import com.oraclejava.werim_lending_app.dao.FranchiseRepository;
import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dto.Franchise;
import com.oraclejava.werim_lending_app.dto.ImgFile;
import com.oraclejava.werim_lending_app.dto.Store;
import com.oraclejava.werim_lending_app.dto.UserInfo;
import com.oraclejava.werim_lending_app.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {

	@Autowired
	private UserInfoRepository userinfoRepository;

	@Autowired
	private FranchiseRepository franchiseRepository;

	@Autowired
	private StoreService storeService;


	@GetMapping("/request")
	public ModelAndView requestStore(ModelAndView mav) {
		mav.setViewName("/user/userLayout");
		mav.addObject("contents", "/user/store/request :: store-request-page");
		return mav;
	}

	@PostMapping("/request")
	public String requestStore(@AuthenticationPrincipal CustomUser user) {
		System.out.println("post");
		UserInfo userinfo = user.getUserinfo();
		userinfo.setStoreState("1");

		userinfoRepository.save(userinfo);

		return "redirect:/store/request";
	}

	@GetMapping("/store-list")
	public ModelAndView storeList(@AuthenticationPrincipal CustomUser user) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/user/userLayout");
		mav.addObject("contents","/user/store/store-list :: store-list-page");

		UserInfo userinfo = user.getUserinfo();
		List<Store> store_list = (List<Store>)storeService.findAllByUserId(userinfo.getUser_id());
		mav.addObject("store_list",store_list);

		//mav.addObject("test",));

		return mav;
	}

	@GetMapping("/register")
	public ModelAndView register(ModelAndView mav, @AuthenticationPrincipal CustomUser user) {
		UserInfo userinfo = user.getUserinfo();

		if( !mav.getModelMap().containsAttribute("store")) {
			Store store = new Store();
			store.setUserId(userinfo.getUser_id());
			mav.addObject("store", store );
		}
		List<Franchise> fran_list = (List<Franchise>) franchiseRepository.findAll();
		mav.addObject("fran_list",fran_list);
		mav.addObject("userinfo", user.getUserinfo());
		mav.addObject("contents", "/user/store/register :: store-register-page");
		mav.setViewName("/user/userLayout");

		return mav;
	}

	@PostMapping("/register")
	public String register(Store store, MultipartFile file) {
		if( !file.isEmpty() ) {
			ImgFile file_info = new ImgFile();

			try {
				file_info.setData(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			file_info.setName(file.getOriginalFilename());
			file_info.setIn_time(new Date());
			file_info.setContentType(file.getContentType());
			storeService.register(store, file_info);
		}
		else
			storeService.register(store, null);

		return "redirect:/store/store-list";
	}

	@PostMapping("/delete/{store_pk}")
	public String delete(@PathVariable int store_pk) {
		storeService.delete(store_pk);

		return "redirect:/store/store-list";
	}
	@GetMapping("/{store_pk}/menu/update")
	public String updateMenu(Model model, @PathVariable("store_pk") int store_pk) {
		model.addAttribute("contents", "/user/store/store-update-menu :: store-update-menu-page");
		model.addAttribute("store", storeService.findById(store_pk));

		return "/user/userLayout";
	}
}
