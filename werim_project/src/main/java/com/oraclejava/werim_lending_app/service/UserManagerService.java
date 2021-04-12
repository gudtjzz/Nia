package com.oraclejava.werim_lending_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.werim_lending_app.dao.UserInfoRepository;
import com.oraclejava.werim_lending_app.dao.UserRoleRepository;
import com.oraclejava.werim_lending_app.dto.UserInfo;
import com.oraclejava.werim_lending_app.dto.UserRole;

@Service
public class UserManagerService {

	@Autowired
	private UserInfoRepository userinfoRepository;
	
	@Autowired
	private UserRoleRepository userroleRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public int register(UserInfo userinfo) {
		UserRole role = new UserRole();
		role.setRole("ROLE_USER");
		role.setUserInfo(userinfo);
		
		userinfo.setPassword(encoder.encode(userinfo.getPassword()));
		
		userinfoRepository.save(userinfo);
		userroleRepository.save(role);
		
		return 1;
	}
	
	public UserInfo getUserInfo(int pk) { 
		return userinfoRepository.findById(pk).get();
	}
	
	public UserInfo getUserInfo(String username) {
		return userinfoRepository.findByUsername(username);
	}
}
