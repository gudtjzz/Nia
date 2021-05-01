package com.oraclejava.werim_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="franchise")
public class Franchise {

	@Id
	private Integer franchise_pk;
	private String name;
}
