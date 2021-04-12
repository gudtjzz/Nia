package com.oraclejava.werim_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="user_role")
@SequenceGenerator(allocationSize = 1, name = "user_role_gen", sequenceName = "user_role_seq")
public class UserRole {
	@Id
	@GeneratedValue(generator = "user_role_gen", strategy = GenerationType.SEQUENCE)
	private int role_id;
	//private int user_id;
	private String role;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
	
}
