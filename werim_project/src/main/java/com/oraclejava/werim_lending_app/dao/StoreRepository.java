package com.oraclejava.werim_lending_app.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;	
import org.springframework.data.repository.CrudRepository;

import com.oraclejava.werim_lending_app.dto.Store;



public interface StoreRepository extends CrudRepository<Store, Integer> {
	public Iterable<Store> findAllByUserId(int user_id);
	
	/*@Query("SELECT b FROM Store b WHERE b.category='-2'and b.logo_file")
	List<Store> findAllOrderBycategory();
	
	@Query("SELECT b FROM Store b WHERE b.category='-2'and b.logo_file")
	List<Store> findAllOrderBylogo_file();
	
	@Query("SELECT b FROM Store b WHERE b.category='5'")
	List<Store> findAllOrderBycategory2();*/
	
	
	List<Store> findByCategory(String category);

	
}

