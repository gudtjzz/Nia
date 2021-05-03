package com.oraclejava.werim_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="menu")
@SequenceGenerator(allocationSize = 1, name = "menu_seq_gen",
sequenceName = "menu_seq")
public class Menu {
	@Id
	@GeneratedValue(generator = "menu_seq_gen", strategy = GenerationType.SEQUENCE)
	private Integer menuPk;
	private Integer storePk;
	private String name;
	private int price;
	private String category;
	
	@OneToOne
	@JoinColumn(name = "img_pk")
	private ImgFile logo_file;
}
