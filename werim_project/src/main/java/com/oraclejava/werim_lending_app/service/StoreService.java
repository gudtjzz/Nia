package com.oraclejava.werim_lending_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.werim_lending_app.dao.ImgFileRepository;
import com.oraclejava.werim_lending_app.dao.StoreRepository;
import com.oraclejava.werim_lending_app.dto.ImgFile;
import com.oraclejava.werim_lending_app.dto.Store;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ImgFileRepository imgFileRepository;
	
	public Store findById(int store_pk) {
		return storeRepository.findById(store_pk).get();
	}
	@Transactional
	public void register(Store store, ImgFile file) {
		if( file != null) {
			file = imgFileRepository.save(file);
			store.setLogo_file(file);
		}
		storeRepository.save(store);
	}
	
	public Iterable<Store> findAllByUserId(int user_id){
		return storeRepository.findAllByUserId(user_id);
	}
	
	@Transactional
	public void delete(int store_pk) {
		Store store = storeRepository.findById(store_pk).get();
		storeRepository.deleteById(store_pk);
		if( store.getLogo_file() != null && 
			imgFileRepository.existsById(store.getLogo_file().getImg_pk()))
			imgFileRepository.deleteById(store.getLogo_file().getImg_pk());
	}
}
