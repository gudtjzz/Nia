package com.oraclejava.werim_lending_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oraclejava.werim_lending_app.dao.ImgFileRepository;
import com.oraclejava.werim_lending_app.dao.MenuRepository;
import com.oraclejava.werim_lending_app.dao.OptionsRepository;
import com.oraclejava.werim_lending_app.dao.StoreRepository;
import com.oraclejava.werim_lending_app.dto.CategoryJSON;
import com.oraclejava.werim_lending_app.dto.CategoryOptionJSON;
import com.oraclejava.werim_lending_app.dto.ImgFile;
import com.oraclejava.werim_lending_app.dto.Menu;
import com.oraclejava.werim_lending_app.dto.MenuJSON;
import com.oraclejava.werim_lending_app.dto.Options;
import com.oraclejava.werim_lending_app.dto.OptionsJSON;
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
	public void registerNUpdateMenu(String json, int store_pk) {
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
				menu.setStorePk(store_pk);
				
				String img = (String)item.get("img");
				ImgFile imgfile = new ImgFile();
				
				if(img.contains("base64")) {
					imgfile.setData(Base64.decodeBase64(img.substring(img.indexOf("base64") + 7, img.length() )) );
					imgfile.setContentType( img.substring(5, img.indexOf("base64")-1 ) );
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
			options_.setStorePk(store_pk);
			
			String pk = (String)map.get("id");
			if( pk != "")
				options_.setOptionsPk( Integer.parseInt(pk) );
			
			options_list.add(options_);
		}

		List<Menu> exist_menu = menuRepository.findByStorePkOrderByCategory(store_pk);
		List<Options> exist_option = optionsRepository.findByStorePk(store_pk);
		
		for(int i=0; i<exist_menu.size(); i++) {
			boolean result = false;
			
			// 새로 들어온 데이터 목록에 디비에 있는 목록이 없다면 지워진 아이템이다
			// 이걸 검사한다
			for(int j=0; j<menu_list.size(); j++) {
				//System.out.println("i : " + i + "j : " + j + " , size : " + exist_menu.size() + " ,size : " + menu_list.size());
				if( menu_list.get(j).getMenuPk() == exist_menu.get(i).getMenuPk() )
					result = true;
			}
			
			// 새로 들어온 목록에 디비에 있던 목록이 없다
			// 그렇다면 지워주자
			if(result == false) {
				menuRepository.delete(exist_menu.get(i));
			}
		}
		
		for(int i=0; i<exist_option.size(); i++) {
			boolean result = false;
			
			// 새로 들어온 데이터 목록에 디비에 있는 목록이 없다면 지워진 아이템이다
			// 이걸 검사한다
			for(int j=0; j<options_list.size(); j++) {
				if( options_list.get(j).getOptionsPk() == exist_option.get(i).getOptionsPk() )
					result = true;
			}
			
			// 새로 들어온 목록에 디비에 있던 목록이 없다
			// 그렇다면 지워주자
			if(result == false) {
				optionsRepository.delete(exist_option.get(i));
			}
		}
		
		imgFileRepository.saveAll(imgfile_list);
		menuRepository.saveAll(menu_list);
		optionsRepository.saveAll(options_list);
	}
	
	@Transactional
	public String getMenuOptionJSON(int store_pk) {
		ObjectMapper mapper = new ObjectMapper();
		CategoryOptionJSON coJ = new CategoryOptionJSON();
		List<Menu> menu_list = (List<Menu>) menuRepository.findByStorePkOrderByCategory(store_pk);
		List<Options> options_list = (List<Options>)optionsRepository.findByStorePk(store_pk);
		CategoryJSON cateJ = new CategoryJSON();
		
		// 카테고리 json 만들기
		for(int i=0; i < menu_list.size(); i++) {
			Menu m = menu_list.get(i);
			MenuJSON mj = new MenuJSON();
			
			if( !m.getCategory().equals(cateJ.getName()) ) {
				if( !cateJ.getItems().isEmpty() )
					coJ.getCategories().add(cateJ);
				cateJ.setName(m.getCategory());
				cateJ.getItems().clear();
			}
			
			mj.setId(m.getMenuPk());
			if(m.getLogo_file() != null) {
				ImgFile img = m.getLogo_file();
				mj.setImg("data:" + img.getContentType() + ";base64," + img.getBase64());
			}else {
				mj.setImg("/images/none.png");
			}
			mj.setName(m.getName());
			mj.setPrice(m.getPrice());
			
			cateJ.getItems().add(mj);
			
			// 마지막인 경우 추가하고 끝내기
			if( i == menu_list.size() - 1 )
				coJ.getCategories().add(cateJ);
		}
		
		/////////      카테고리 만들기 끝
		
		/////// 옵션 json 만들기
		
		for(int i=0; i<options_list.size(); i++) {
			Options op = options_list.get(i);
			OptionsJSON oj = new OptionsJSON();
			
			oj.setId(op.getOptionsPk());
			oj.setName(op.getName());
			oj.setPrice(op.getPrice());
			
			coJ.getOptions().add(oj);
		}
		
		///// 옵션 json 만들기 끝
				
		try {
			return mapper.writeValueAsString(coJ);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
}
