package com.oraclejava.werim_lending_app.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "img_file")
@Setter
@Getter
@ToString
@SequenceGenerator(allocationSize = 1, name = "img_file_seq_gen",
sequenceName = "img_file_seq")
public class ImgFile {
	@Id
	@GeneratedValue(generator = "img_file_seq_gen", strategy = GenerationType.SEQUENCE)
	private Integer img_pk;
	private String name;
	private byte[] data;
	private String contentType;
	
	@Column(nullable = false)
	private Date in_time;
	
	@Transient
	private String base64;
	
	public String getBase64() {
		return Base64.encodeBase64String(data);
	}
	//@OneToOne(mappedBy = "logo_file",cascade = CascadeType.ALL)
	//private Store store;
	
}
