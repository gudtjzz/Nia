package com.oraclejava.werim_lending_app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.oraclejava.werim_lending_app.dto.ImgFile;


public interface ImgFileRepository extends CrudRepository<ImgFile, Integer> {
	
//public Iterable<ImgFile> findAllByimg_pk(int img_pk);
	

}
