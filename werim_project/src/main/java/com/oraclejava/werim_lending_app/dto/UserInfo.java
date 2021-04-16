package com.oraclejava.werim_lending_app.dto;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user_info")
@SequenceGenerator(allocationSize = 1, name = "user_info_gen", sequenceName = "user_info_seq")
public class UserInfo {

	@Id
	@GeneratedValue(generator = "user_info_gen", strategy = GenerationType.SEQUENCE)
	private int user_id;
	
	@Length(min = 5, message = "아이디는 5자 이상 입력하세요")
	@NotEmpty(message = "아이디를 입력하세요")
	private String username;
	
	@Length(min = 5, message = "패스워드는 5자 이상 입력하세요")
	@NotEmpty(message = "패스워드를 입력하세요")
	private String password;
	
	@Transient
	private String password2;
	
	@Email(message = "이메일 형식이 올바르지 않습니다")
	@NotEmpty(message = "이메일을 입력하세요")
	private String mail_address;
	
	private String tel;
	
	private int zonecode;
	
	private String address;
	
	private String address2;
	
	@Column
	private String store_state = "0";
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles;
	
}
