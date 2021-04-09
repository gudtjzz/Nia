package com.oraclejava.werim_lending_app.dao;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.werim_lending_app.dto.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, Integer> {
	UserInfo findByUsername(String username);

}
