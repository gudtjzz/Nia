package com.oraclejava.werim_lending_app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.werim_lending_app.dto.Options;



public interface OptionsRepository extends CrudRepository<Options, Integer> {
	public List<Options> findByStorePk(int storePk);
}

