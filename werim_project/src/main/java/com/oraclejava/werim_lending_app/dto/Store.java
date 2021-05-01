package com.oraclejava.werim_lending_app.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@SequenceGenerator(allocationSize = 1, name = "store_seq_gen",
sequenceName = "store_seq")
@Table(name="store")
public class Store {

	@Id
	@GeneratedValue(generator = "store_seq_gen", strategy = GenerationType.SEQUENCE)
	private Integer store_pk;
	
	@Column(name = "user_id")
	private Integer userId;
	private String name;
	private String owner_name;
	private String address;
	private String tel;
	private String business_number;
	private String category;
	private Integer franchise;
	private Integer avg_delivery_time;
	private Integer min_price;
	private String oper_time;
	private String origin_info;
	
	@OneToOne
	@JoinColumn(name = "img_pk")
	private ImgFile logo_file;
}
