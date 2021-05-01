package com.oraclejava.werim_lending_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.werim_lending_app.dao.ImgFileRepository;
import com.oraclejava.werim_lending_app.dao.MenuRepository;
import com.oraclejava.werim_lending_app.dao.OptionsRepository;
import com.oraclejava.werim_lending_app.dao.StoreRepository;
import com.oraclejava.werim_lending_app.dto.ImgFile;
import com.oraclejava.werim_lending_app.dto.Menu;
import com.oraclejava.werim_lending_app.dto.Options;
import com.oraclejava.werim_lending_app.dto.Store;

@Service
public class StoreService {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ImgFileRepository imgFileRepository;
	
	@Autowired
	private MenuRepository menuRepository;
	
	@Autowired
	private OptionsRepository optionsRepository;
	
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
	
	@Transactional
	public void registerNUpdateMenu(String json) {
		JacksonJsonParser parser = new JacksonJsonParser();
		Map jsonMap = parser.parseMap(json);
		
		List<Menu> menu_list = new ArrayList<>();
		List<Options> options_list = new ArrayList<>();
		List<ImgFile> imgfile_list = new ArrayList<>();
		
		List categories = (List)jsonMap.get("categories");
		List options = (List)jsonMap.get("options");		
		
		for(int i=0; i<categories.size(); i++) {
			Map map = (Map)categories.get(i);
			String category_name = (String)map.get("name");
			List items = (List)map.get("items");
			
			for(int j=0; j<items.size(); j++) {
				Map item = (Map)items.get(j);
				Menu menu = new Menu();
				String pk = (String)item.get("id");
				if( pk != "")
					menu.setMenuPk( Integer.parseInt(pk) );
				menu.setName( (String)item.get("name") );
				menu.setPrice( Integer.parseInt((String)item.get("price")) );
				menu.setCategory(category_name);
				
				String img = (String)item.get("img");
				ImgFile imgfile = new ImgFile();
				
				if(img.contains("base64")) {	
					imgfile.setData(img.substring(img.indexOf("base64") + 7, img.length() ).getBytes() );
					imgfile.setContentType( img.substring(5, img.indexOf("base64") ) );
					menu.setLogo_file(imgfile);
				}else {
					imgfile = null;
				}
				if(imgfile != null)
					imgfile_list.add(imgfile);
				menu_list.add(menu);
			}
		}
		
		for(int i=0; i<options.size(); i++) {
			Map map = (Map)options.get(i);
			Options options_ = new Options();
			
			options_.setName( (String)map.get("name") );
			options_.setPrice( Integer.parseInt((String)map.get("price")) );
			String pk = (String)map.get("id");
			if( pk != "")
				options_.setOptionsPk( Integer.parseInt(pk) );
			
			options_list.add(options_);
		}

		imgFileRepository.saveAll(imgfile_list);
		menuRepository.saveAll(menu_list);
		optionsRepository.saveAll(options_list);
	}
}
