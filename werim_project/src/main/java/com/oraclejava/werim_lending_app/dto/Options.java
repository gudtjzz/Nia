package com.oraclejava.werim_lending_app.dto;

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
@Table(name = "options")
@SequenceGenerator(allocationSize = 1, name = "options_seq_gen",
sequenceName = "options_seq")
public class Options {
	@Id
	@GeneratedValue(generator = "options_seq_gen", strategy = GenerationType.SEQUENCE)
	private Integer optionsPk;
	private Integer storePk;
	private String name;
	private int price;
}
