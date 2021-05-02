package com.oraclejava.werim_lending_app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.werim_lending_app.dto.Menu;



public interface MenuRepository extends CrudRepository<Menu, Integer> {
	public List<Menu> findByStorePkOrderByCategory(int storePk);
}

