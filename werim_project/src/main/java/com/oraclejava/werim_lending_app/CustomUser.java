package com.oraclejava.werim_lending_app;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.oraclejava.werim_lending_app.dto.UserInfo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomUser extends User {

	private UserInfo userinfo;
	
	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, UserInfo userinfo) {
		super(username, password,true,true,true,true, authorities);
		this.userinfo = userinfo;
	}	
	
}
